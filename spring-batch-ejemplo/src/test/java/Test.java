import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.batch.core.launch.support.CommandLineJobRunner;

public class Test {
	private static Log log = LogFactoryImpl.getLog(Test.class);

	public static void main(String[] args) {		
		//String[] args0 ={"classpath*:jobs/spring-batch-demo0.xml", "trabajoBatch"};
		//String[] args1 ={"classpath*:jobs/spring-batch-demo1.xml", "trabajoBatch"};
		//String[] args2 ={"classpath*:jobs/spring-batch-demo2.xml", "trabajoBatch"};
		//String[] args4 ={"classpath*:jobs/spring-batch-demo3.xml", "trabajoBatch"};
		//String[] args5 ={"classpath*:jobs/spring-batch-demo5.xml", "trabajoBatch"};
		//String[] args6 ={"classpath*:jobs/spring-batch-demo6.xml", "trabajoBatch"};
		//String[] args7 ={"classpath*:jobs/spring-batch-demo7.xml", "trabajoBatchMultithread"};
		//String[] args8 ={"classpath*:jobs/spring-batch-demo8.xml", "trabajoBatch"};
		//String[] args9 ={"classpath*:jobs/spring-batch-demo9.xml", "trabajoBatch"};
		String[] args10 ={"classpath*:jobs/spring-batch-demo10.xml", "trabajoBatch"};
		log.info("Entrando con parámetros: " + StringUtils.join(args10, ","));
		CommandLineJobRunner.main(args10);
	}
}
