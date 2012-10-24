package springbatch.demo10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class BeanExtractorCampos<T> implements FieldExtractor<T> {


	/**
	 * @see org.springframework.batch.item.file.transform.FieldExtractor#extract(java.lang.Object)
	 */
	public Object[] extract(T item) {

		Planeta planeta = (Planeta) item;
		BeanWrapper bw = new BeanWrapperImpl(item);
		
		String contrato = bw.getPropertyValue("contrato").toString();
		String deuda = bw.getPropertyValue("deuda").toString();
		
		Object[] valores = {"Contrato:" + contrato, deuda};
		
		return valores;
	}

}

