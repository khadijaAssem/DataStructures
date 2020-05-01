package eg.edu.alexu.csd.filestructure.btree;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import javax.management.RuntimeErrorException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SearchEngine implements ISearchEngine {
	private IBTree<String, List<ISearchResult>> btree;
	private int minDegree;
	public SearchEngine(int minDegree) {
		btree = new BTree<>(minDegree);
		this.minDegree = minDegree;
	}
	@Override
	public void indexWebPage(String filePath) {
		if(filePath == null || filePath.isEmpty())
			throw new RuntimeErrorException(new Error());
		btree = parse(filePath,btree);
	}

	public void print(IBTreeNode root) {
		for (int i = 0; i < root.getNumOfKeys(); i++) {
			System.out.println(root.getKeys().get(i));
		}
		System.out.println();
		if (root.isLeaf()) {
			return;
		} else {
			List<IBTreeNode> child = root.getChildren();
			for (int l = 0; l < 10; l++) {
				IBTreeNode node = new BTreeNode<>();
				for (int i = 0; i < child.size(); i++) {
					node = child.get(i);
//					for (int j = 0; j < node.getNumOfKeys(); j++) {
					System.out.println(node.getKeys() + " "+node.getValues());
//					}
				}
				child = node.getChildren();
			}
		}
	}

	private IBTree<String, List<ISearchResult>> parse(String path,IBTree tempTree) {
		File xmldoc = new File(path);
		if (!xmldoc.exists()) {
			throw new RuntimeErrorException(new Error());
		}
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(xmldoc);
			Element root = doc.getDocumentElement();
			NodeList rootDocuments = root.getElementsByTagName("doc");
			for (int i = 0; i < rootDocuments.getLength(); i++) {
				Node n = rootDocuments.item(i);
				String body = n.getTextContent();
				Node attributeID = n.getAttributes().item(0);
				tempTree = getRank(body,attributeID.getNodeValue(),tempTree);
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempTree;
	}

	int SUM = 0;

	private IBTree<String, List<ISearchResult>> getRank(String body,String ID,IBTree<String, List<ISearchResult>>tempTree) {
		HashMap<String, Integer> rank = new HashMap<>();
		body = body.trim();
		String[] words = body.split("\\s+");
		for (int i = 0; i < words.length; i++) {
			if (rank.get(words[i].toLowerCase()) == null) {
				rank.put(words[i].toLowerCase(), 1);
			} else {
				rank.put(words[i].toLowerCase(), rank.get(words[i].toLowerCase()) + 1);
			}
		}
		for (String s : rank.keySet()){
			if(tempTree.search(s)==null){
				List<ISearchResult> r = new ArrayList<>();
				r.add(new SearchResult(ID,rank.get(s)));
				tempTree.insert(s, r);
			}
			else {
				tempTree.search(s).add(new SearchResult(ID,rank.get(s)));
			}
		}
		return tempTree;
	}
	@Override
	public void indexDirectory(String directoryPath) {
		if(directoryPath == null || directoryPath.isEmpty())
			throw new RuntimeErrorException(new Error());
		File folder = new File(directoryPath);
		if(!folder.exists())
			throw new RuntimeErrorException(new Error());
		if(folder.isDirectory()){
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isDirectory())
					this.indexDirectory(listOfFiles[i].getPath());
				else
					this.indexWebPage(listOfFiles[i].getPath());
			}
		}else
			throw new RuntimeErrorException(new Error());

	}

	@Override
	public void deleteWebPage(String filePath) {
		if (filePath == null || filePath == "" || !new File(filePath).exists())
			throw new RuntimeErrorException(null);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Document document = builder.parse(filePath);
			document.getDocumentElement().normalize();
			// Element root = document.getDocumentElement();
			NodeList nList = document.getElementsByTagName("doc");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node node = nList.item(temp);
				// System.out.println(""); //Just a separator
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					// Print each employee's detail
					Element eElement = (Element) node;
					String id = eElement.getAttribute("id");
					String value = eElement.getTextContent();
					value = value.toLowerCase();
					ArrayList<String> words = new ArrayList<String>();
					StringTokenizer t = new StringTokenizer(value);
					String word = "";
					while (t.hasMoreTokens()) {
						word = t.nextToken();
						words.add(word.toLowerCase());
					}
					for(int i=0;i<words.size();i++) {
						List<ISearchResult> list = btree.search(words.get(i));
						if(list!=null) {
							for(int j=0;j<list.size();j++) {
								if(list.get(j).getId().equals(id)) {
									list.remove(j);
									break;
								}
							}
						}
					}
				}
			}
		}catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void traverse(IBTreeNode<String,List<ISearchResult>> node) {
		int i;
		for (i = 0; i < node.getNumOfKeys(); i++) {
			if (node.isLeaf()== false)
				traverse((IBTreeNode) node.getChildren().get(i));
			for(int iInKeys =0;iInKeys<node.getKeys().size();iInKeys++){
				if(btree.search((String) node.getKeys().get(iInKeys))!=null) {
					List<ISearchResult> list = btree.search(((String) node.getKeys().get(iInKeys)).toLowerCase());
					for(int j = 0;j<node.getValues().get(iInKeys).size();j++){
						for(int iInList = 0;iInList<list.size();iInList++)
							if(node.getValues().get(iInKeys).get(j).getId().equals(list.get(iInList).getId()))
								list.remove(iInList);
					}
				}
			}
		}
		if (node.isLeaf() == false)
			traverse((IBTreeNode) node.getChildren().get(i));
	}

	@Override
	public List<ISearchResult> searchByWordWithRanking(String word) {
		if (word == null) throw new RuntimeErrorException(null);
		if (btree.search(word.toLowerCase())==null) return new ArrayList<>();
		return btree.search(word.toLowerCase());
	}

	@Override
	public List<ISearchResult> searchByMultipleWordWithRanking(String sentence) {
		if (sentence == null) throw new RuntimeErrorException(null);
		if (btree.getRoot()==null) return new ArrayList<>();
		sentence.trim();
		sentence = sentence.toLowerCase();
		String[] words = sentence.split("\\s+");
		List<ISearchResult> output = new ArrayList<>();
		for(int i=0;i<words.length;i++){
			if(btree.search(words[i])!=null)
				output.addAll(btree.search(words[i]));
		}
		return output;
	}
}