package moodledownloader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MoodleDownloader {

    private int idx, nlinks;
    private String basefolder;
    private String folder;
    private String folder_tmp;
    private String moodleURL;
    private Map<String, String> cookies;
    private boolean cookiesneeded;
    private boolean inSection;
    private Map<String, String> sections;

    private static MainJFrame mainframe;

    public static void main(String[] args) {
        mainframe = new MainJFrame();
        mainframe.setVisible(true);
    }

    void downloadMoodle(String url) {
        cookiesneeded = false;
        inSection = false;
        moodleURL = url;
        folder_tmp = "";
        sections = new HashMap<>();
        Document doc = moodleLogin();
        countLinks(doc);
        generatePDF();
        parseLinks(doc);
        parseSections(doc);
        mainframe.showCompleted();
    }

    private void parseSections(Document doc) {
        inSection = true;
        basefolder = folder;
        for (Map.Entry<String, String> entry : sections.entrySet()) {
            mainframe.setOut("");
            mainframe.setOut("*** Downloading section: " + entry.getValue());
            moodleURL = entry.getKey();
            folder_tmp = basefolder + "\\" + entry.getValue() + "\\";
            Path path = Paths.get(folder_tmp);
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            doc = moodleLogin();
            folder = folder_tmp;
            countLinks(doc);
            generatePDF();
            parseLinks(doc);
        }
    }

    private void generatePDF() {
        mainframe.setOut("[0/" + nlinks + "] Moodle.pdf");

        String command = "wkhtmltopdf ";

        if (cookiesneeded) {
            StringBuilder commandBuilder = new StringBuilder("wkhtmltopdf ");
            for (Map.Entry<String, String> cookie : cookies.entrySet()) {
                String key = cookie.getKey();
                String value = cookie.getValue();
                commandBuilder.append(String.format("--cookie %s %s ", key, value));
            }
            command = commandBuilder.toString();
        }

        command += moodleURL + " \"000 Moodle.pdf.tmp\"";
        String source = System.getProperty("user.dir") + "\\000 Moodle.pdf.tmp";
        String target = folder + "000 Moodle.pdf";

        try {
            Process proc = Runtime.getRuntime().exec(command);
            proc.waitFor();
            Files.move(Paths.get(source), Paths.get(target));
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(MoodleDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    private void countLinks(Document doc) {
        Elements links = doc.select("a[href]");
        nlinks = 0;
        String urllink;
        for (Element e : links) {
            if (e.toString().contains("/assign/") || e.toString().contains("/folder/")
                    || e.toString().contains("/forum/") || e.toString().contains("/page/")
                    || e.toString().contains("/resource/") || e.toString().contains("/url/")) {
                nlinks++;
            }
            if (e.toString().contains("section=")) {
                if (!inSection) {
                    urllink = e.text();
                    urllink = urllink.replaceAll("[\\\\/:*?\"<>|]", "-");
                    urllink = urllink.replaceAll("[.]", "");
                    sections.put(e.attr("href"), urllink);
                }
            }
        }
        mainframe.setMaximumProgressBar(nlinks);
    }

    private Document moodleLogin() {
        trustEveryone();

        Document doc = null;

        try {
            Response response = Jsoup.connect(moodleURL).method(Method.GET).maxBodySize(0).timeout(0).execute();
            doc = response.parse();
            cookies = response.cookies();
            //System.out.println(doc.html()); //JVS
            if (doc.html().contains("loginform")) {
            //if (doc.html().contains("Nombre de usuario")) { //JVS
                cookiesneeded = true;
                String loginURL = doc.baseUri();

                response = Jsoup.connect(loginURL).method(Method.GET).maxBodySize(0).timeout(0).execute();
                doc = response.parse();
                cookies = response.cookies();

                response = Jsoup.connect(loginURL)
                        .data("username", mainframe.getUsername())
                        .data("password", mainframe.getPassword())
                        .cookies(cookies).maxBodySize(0).timeout(0).method(Method.POST).execute();
                doc = response.parse();

                Map<String, String> tmpcookies;
                tmpcookies = response.cookies();

                String cookieuser = null, cookiesession = null;

                for (Map.Entry<String, String> entrySet : tmpcookies.entrySet()) {
                    String key = entrySet.getKey();
                    String value = entrySet.getValue();
                    if (key.contains("MoodleSession")) {
                        cookieuser = key;
                        cookiesession = value;
                    }
                }

                cookies.put(cookieuser, cookiesession);

                response = Jsoup.connect(moodleURL).method(Method.GET).cookies(cookies).maxBodySize(0).timeout(0).execute();
                doc = response.parse();

            }

            if (doc.html().contains("policy")) {
                cookiesneeded = true;
                Elements links = doc.select("form[action]");
                String action = "";
                for (Element e : links) {
                    if (e.attr("action").contains("policy")) action = e.attr("action");
                }

                String sesskey = "";
                links = doc.select("input[name=\"sesskey\"]");
                for (Element e : links) {
                    sesskey = e.attr("value");
                }

                String split[] = moodleURL.split("/");
                String policyURL = split[0] + "//" + split[2] + "/user/" + action;

                response = Jsoup.connect(policyURL)
                        .data("agree", "1")
                        .data("sesskey", sesskey)
                        .cookies(cookies).maxBodySize(0).timeout(0).method(Method.POST).execute();
                doc = response.parse();
            }

            if (doc.html().contains("Inicia sessió com a visitant")) {
            //if (doc.html().contains("guestlogin")) {
            	 cookiesneeded = true;
                 String loginURL = doc.baseUri();

                 response = Jsoup
                         .connect(loginURL)//"https://dit.gonzalonazareno.org/moodle/course/view.php?id=11")
                         .method(Connection.Method.GET)
                         .execute();
                 doc = response.parse();
                 cookies = response.cookies();

//                 response = Jsoup.connect("https://dit.gonzalonazareno.org/moodle/login/index.php")
//                         .data("logintoken", "nVxRsroAFIkuk2WoqHqtsVyT4seBv5jq")
//                         .data("username", mainframe.getUsername())
//                         .data("password", mainframe.getPassword())
//                         .cookies(cookies)
//                         .method(Connection.Method.POST)
//                         .followRedirects(true)
//                         .execute();
//
//                 doc = response.parse();
//
//                 Map<String, String> tmpcookies;
//                 tmpcookies = response.cookies();
//
//                 String cookieuser = null, cookiesession = null;
//
//                 for (Map.Entry<String, String> entrySet : tmpcookies.entrySet()) {
//                     String key = entrySet.getKey();
//                     String value = entrySet.getValue();
//                     if (key.contains("MoodleSession")) {
//                         cookieuser = key;
//                         cookiesession = value;
//                     }
//                 }

                 cookies.put("MoodleSession", "kvphg0e10cd4qsviejt9lffh93");
                 //cookies.put("MoodleSessionmoodle2postgres", "5691ijjurpeqcedsthsu89cm48");

                 response = Jsoup.connect(moodleURL).method(Method.GET).cookies(cookies).maxBodySize(0).timeout(0).execute();
                 doc = response.parse();
            }
            
            folder = folder_tmp.equals("") ? mainframe.getFolder() + "\\" : folder_tmp;

        } catch (IOException ioe) {
            System.out.println("ERROR: " + ioe.toString());
        }

        return doc;
    }

    private void parseLinks(Document doc) {
        Elements links = doc.select("a[href]");
        String name, link, type = null;
        idx = 1;

        for (Element e : links) {
            link = e.attr("href");
            name = e.text().replaceAll("[\\\\/:*?\"<>|]", "-");
            String content = e.toString();

            if (content.contains("/assign/")) {
                if (name.contains(" Tarea")) name = name.replace(" Tarea", "");
                if (name.contains(" Tasca")) name = name.replace(" Tasca", "");
                downloadAssignment(name, link);
                idx++;
            }
            if (content.contains("/folder/")) {
                if (name.contains(" Carpeta")) name = name.replace(" Carpeta", "");
                downloadFolder(name, link);
                idx++;
            }
            if (content.contains("/forum/")) {
                if (name.contains(" Foro")) name = name.replace(" Foro", "");
                if (name.contains(" Fòrum")) name = name.replace(" Fòrum", "");
                downloadForum(name, link);
                idx++;
            }
            if (content.contains("/page/")) {
                if (name.contains(" Página")) name = name.replace(" Página", "");
                if (name.contains(" Pàgina")) name = name.replace(" Pàgina", "");
                downloadPage(name, link);
                idx++;

            }
            if (content.contains("/resource/")) {
                if (name.contains(" Archivo")) name = name.replace(" Archivo", "");
                if (name.contains(" Fitxer")) name = name.replace(" Fitxer", "");

                if (content.contains("archive-")) type = ".zip";
                if (content.contains("avi-")) type = ".avi";
                if (content.contains("bmp-")) type = ".bmp";
                if (content.contains("document-")) type = ".doc";
                if (content.contains("flash-")) type = ".swf";
                if (content.contains("gif-")) type = ".gif";
                if (content.contains("jpeg-")) type = ".jpg";
                if (content.contains("mp3-")) type = ".mp3";
                if (content.contains("mpeg-")) type = ".mpg";
                if (content.contains("powerpoint-")) type = ".ppt";
                if (content.contains("png-")) type = ".png";
                if (content.contains("pdf-")) type = ".pdf";
                if (content.contains("quicktime-")) type = ".mov";
                if (content.contains("spreadsheet-")) type = ".xls";
                if (content.contains("sourcecode-")) type = ".txt";
                if (content.contains("text-")) type = ".txt";
                if (content.contains("unknown-")) type = "";
                if (content.contains("writer-")) type = ".odt";
                if (content.contains("impress-")) type = ".odp";
                if (content.contains("calc-")) type = ".ods";
                if (type == null) type = ".pdf";

//                if (!type.equals(".pdf")){
                	System.out.println(name);
                downloadResource(name, link, type);
//                }
                idx++;
            }
            if (content.contains("/url/")) {
                if (name.contains(" URL")) name = name.replace(" URL", "");
                downloadURL(name, link);
                idx++;
            }
        }
    }

    private void trustEveryone() {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException | KeyManagementException ignored) {
        }
    }

    private void downloadFolder(String name, String link) {
        mainframe.setProgressBar(idx);
        mainframe.setOut("[" + idx + "/" + nlinks + "] [Carpeta] " + name);

        try {
            Document carpeta = Jsoup.connect(link).cookies(cookies).maxBodySize(0).timeout(0).get();
            Elements links = carpeta.select("a[href]");

            for (Element e : links) {
                if (e.toString().contains("/pluginfile.php/")) {
                    String lnk = e.attr("href");
                    String nam = "";

                    if (!e.text().isEmpty()) {
                        nam = String.format("%03d", idx) + " [Fitxer] " + e.text();
                        mainframe.setOut("[" + idx + "/" + nlinks + "] [Fitxer] " + e.text());
                    }

                    downloadFile(nam, lnk);
                }
            }
        } catch (IOException ioe) {
            System.out.println("ERROR: " + ioe.toString());
        }
    }

    private void downloadURL(String name, String link) {
        name = name.replaceAll("[\\\\/:*¿?\"<>|]", "-");
        name = name.replaceAll("[à]", "a");
        name = name.replaceAll("[é]", "e");
        name = name.replaceAll("[í]", "i");
        name = name.replaceAll("[ó]", "o");
        name = name.replaceAll("[ç]", "c");

        mainframe.setProgressBar(idx);
        mainframe.setOut("[" + idx + "/" + nlinks + "] [URL] " + name);

        try {
            Document urlcontent = Jsoup.connect(link).cookies(cookies).timeout(0).maxBodySize(0).get();

            PrintWriter fitxer = new PrintWriter(new FileWriter(folder + "/" + String.format("%03d", idx) + " [URL] " + name.replaceAll("[()']", "") + ".url"));
            Elements content = urlcontent.select("div.urlworkaround > a");
            // TODO: Revisar select de frame, ya que falla si abre nueva web con frames
            Elements frame = urlcontent.select("frame");

            if (content.size() > 0) {
                fitxer.println("[InternetShortcut]");
                fitxer.println("URL=" + content.attr("href"));
            } else if (frame.size() > 0) {
                String resourcelink;
                for (Element e : frame) {
                    resourcelink = e.attr("src");
                    if (!resourcelink.contains("frameset")) {
                        fitxer.println("[InternetShortcut]");
                        fitxer.println("URL=" + resourcelink);
                    }
                }
            } else {
                fitxer.println("[InternetShortcut]");
                fitxer.println("URL=" + urlcontent.location());
            }

            fitxer.close();
        } catch (IOException ioe) {
            mainframe.setOut("ERROR: " + ioe.toString());
        }
    }

    private void downloadPage(String name, String link) {
        mainframe.setProgressBar(idx);
        mainframe.setOut("[" + idx + "/" + nlinks + "] [Pàgina] " + name);

        try {
            Document forum = Jsoup.connect(link).cookies(cookies).maxBodySize(0).timeout(0).get();
            Elements content = forum.select("div.box > div.no-overflow");

            BufferedWriter fileforum = new BufferedWriter(new FileWriter(folder + "/" + String.format("%03d", idx) + " [Pàgina] " + name + ".html"));
            fileforum.write("<html><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />" + content.html() + "</html>");
            fileforum.close();
        } catch (IOException ioe) {
            System.out.println("ERROR: " + ioe.toString());
        }
    }

    private void downloadAssignment(String name, String link) {
        mainframe.setProgressBar(idx);
        mainframe.setOut("[" + idx + "/" + nlinks + "] [Tasca] " + name);

        Document forum = null;

        try {
            forum = Jsoup.connect(link).cookies(cookies).maxBodySize(0).timeout(0).get();
            Elements content = forum.select("div.box > div.no-overflow");

            BufferedWriter fileforum = new BufferedWriter(new FileWriter(folder + "/" + String.format("%03d", idx) + " [Tasca] " + name + ".html"));
            fileforum.write("<html><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />" + content.html() + "</html>");
            fileforum.close();
        } catch (IOException ioe) {
            System.out.println("ERROR: " + ioe.toString());
        }

        assert forum != null;
        Elements resources = forum.select("div.box > div.no-overflow > a");
        String resourcelink = resources.attr("href");
        if (resourcelink.contains(".pdf")) {
            mainframe.setOut("[" + idx + "/" + nlinks + "] [Fitxer] " + name);
            downloadFile(name, resourcelink);
        }
    }

    private void downloadForum(String name, String link) {
        mainframe.setProgressBar(idx);
        mainframe.setOut("[" + idx + "/" + nlinks + "] [Fòrum] " + name);

        try {
            Document forum = Jsoup.connect(link).cookies(cookies).maxBodySize(0).timeout(0).get();
            Elements content = forum.select("div.box > div.no-overflow");

            BufferedWriter fileforum = new BufferedWriter(new FileWriter(folder + "/" + String.format("%03d", idx) + " [Fòrum] " + name + ".html"));
            fileforum.write("<html><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />" + content.html() + "</html>");
            fileforum.close();
        } catch (IOException ioe) {
            System.out.println("ERROR: " + ioe.toString());
        }
    }

    private void downloadResource(String name, String link, String type) {
        String iname = String.format("%03d", idx) + " [Fitxer] " + name + type;

        mainframe.setProgressBar(idx);
        mainframe.setOut("[" + idx + "/" + nlinks + "] [Fitxer] " + name + type);

        downloadFile(iname, link);
    }

    private void downloadFile(String name, String link) {
        try {
            Document doc1 = moodleLogin();
            Response resultImageResponse;
            if (cookiesneeded) {
                resultImageResponse = Jsoup.connect(link).cookies(cookies).ignoreContentType(true).maxBodySize(0).timeout(0).execute();
            } else {
                resultImageResponse = Jsoup.connect(link).ignoreContentType(true).maxBodySize(0).timeout(0).execute();
            }
            Document doc = resultImageResponse.parse();
            if (doc.html().contains("resourcecontent resourcepdf"))
                downloadFile(name, doc.select("object").attr("data"));
            else if (doc.html().contains("resourceworkaround"))
                downloadFile(name, doc.select(".resourceworkaround > a").attr("href"));
            else {
                FileOutputStream out = new FileOutputStream(new File(folder, name));
                //JVS
                if (cookiesneeded) {
                    resultImageResponse = Jsoup.connect(link).cookies(cookies).ignoreContentType(true).maxBodySize(0).timeout(0).execute();
                } else {
                    resultImageResponse = Jsoup.connect(link).ignoreContentType(true).maxBodySize(0).timeout(0).execute();
                }
                // END JVS
                out.write(resultImageResponse.bodyAsBytes());
            }
        } catch (IOException ioe) {
            System.out.println("ERROR: " + ioe.toString());
        }
    }

}
