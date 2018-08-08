package bean;
//--------------------------------------------------------------------------------------------
//通过引入DOM和反射机制后，可以在XMLUtil中实现读取XML文件并根据存储在XML文件中的类名获取对应的对象
//XML操作工具类
//--------------------------------------------------------------------------------------------
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtil1 {
	
	//该方法用于从XML配置文件中提取具体类名，并返回一个实例对象
	public static Object getBean(){
	
		try{
			//创建DOM文档对象
			DocumentBuilderFactory dFactory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=dFactory.newDocumentBuilder();
			Document doc;
			doc=builder.parse(new File("config1.xml"));
			
			//获取包含类名的文本节点
			//将具体类的类名存储在XML文件中
			NodeList nl=doc.getElementsByTagName("className");
			Node classNode=nl.item(0).getFirstChild();
			String cName=classNode.getNodeValue();			
			
			//通过类名生成实例对象并将其返回
			Class <?>c=Class.forName(cName);
			Object obj=c.newInstance();
			return obj;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
