package bean;
//--------------------------------------------------------------------------------------------
//ͨ������DOM�ͷ�����ƺ󣬿�����XMLUtil��ʵ�ֶ�ȡXML�ļ������ݴ洢��XML�ļ��е�������ȡ��Ӧ�Ķ���
//XML����������
//--------------------------------------------------------------------------------------------
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtil1 {
	
	//�÷������ڴ�XML�����ļ�����ȡ����������������һ��ʵ������
	public static Object getBean(){
	
		try{
			//����DOM�ĵ�����
			DocumentBuilderFactory dFactory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=dFactory.newDocumentBuilder();
			Document doc;
			doc=builder.parse(new File("config1.xml"));
			
			//��ȡ�����������ı��ڵ�
			//��������������洢��XML�ļ���
			NodeList nl=doc.getElementsByTagName("className");
			Node classNode=nl.item(0).getFirstChild();
			String cName=classNode.getNodeValue();			
			
			//ͨ����������ʵ�����󲢽��䷵��
			Class <?>c=Class.forName(cName);
			Object obj=c.newInstance();
			return obj;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
