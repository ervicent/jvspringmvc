package tesis.playon.web.datamodel;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;
import java.util.List;  
import tesis.playon.web.model.Playa;

public class PlayaDataModel extends ListDataModel<Playa> implements SelectableDataModel<Playa> {

    public PlayaDataModel() {  
    }  
  
    public PlayaDataModel(List<Playa> data) {  
        super(data);  
    } 
      
    @Override  
    public Playa getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        @SuppressWarnings("unchecked")
        List<Playa> playas = (List<Playa>) getWrappedData();  
          
        for(Playa playa : playas) {  
            if((String.valueOf(playa.getId())).equals(rowKey))  
                return playa;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Playa playa) {  
        return playa.getId();  
    }  

}
