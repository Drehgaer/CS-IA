package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class XmlExporter {

    public static void exportToXml(Product[] tableProducts, String filePath) {
        List<Product> products = Arrays.asList(tableProducts);
        try {
            JAXBContext context = JAXBContext.newInstance(ProductListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrap the list of entities in a wrapper object
            ProductListWrapper wrapper = new ProductListWrapper();
            wrapper.setProducts(products);

            // Marshal the wrapper object to XML
            marshaller.marshal(wrapper, new File("exports/" + filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
