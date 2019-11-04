import java.util.ArrayList;
import java.util.Arrays;

//text file 7 takes too long

public class Hash {

	private  Node[] hashArray;
	private String word;
	private int popArraySize;
	private int size;
	private String[] popArray;
	ArrayList<Node>popArrayList;

	public Hash() {
		this.word=word;
		popArrayList=new ArrayList<>();
		popArraySize=0;
		hashArray = new Node [20011];
		popArray = new String[10000];
		size=0;
	}
//Gets size of unique words
	public int getSize() {
		return size;
	}
//Adds words and handles collisions
	public void add(int num, String word) {
		int wordCode = word.hashCode()%hashArray.length;
		if(wordCode<0) {
			wordCode= wordCode+hashArray.length;
		}
		Node newNode= new Node(word,num);
		if((hashArray[wordCode]!=null)&&(hashArray[wordCode].getData().equals(word))) {
			hashArray[wordCode].setCount(hashArray[wordCode].getCount()+num);
			Node temp=hashArray[wordCode];
			popArrayList.remove(temp);
			sort(hashArray[wordCode]);
		}
		else if(hashArray[wordCode]==null) {
			hashArray[wordCode]=newNode;
			sort(hashArray[wordCode]);
			size++;
		}
		else {
			Node current= hashArray[wordCode];
			if(current.next==null) {
				current.next=newNode;
				sort(newNode);
				size++;
			}
			else {
				while(current.next!=null) {
					if(current.getData().equals(newNode.getData())) {
						current.setCount(current.getCount()+num);
						Node temp =current;
						popArrayList.remove(temp);
						sort(current);
					}
					current=current.next;
				}
				current.next=newNode;
				sort(newNode);
				size++;
			}
		}
	}
//Gets the value of how many times word is in file
	public int getValue(String word) {
		int wordCode = Math.abs(word.hashCode()%hashArray.length);
		Node node = hashArray[wordCode];
		if(node!=null) {
			if(node.getData().equals(word)){
				return node.getCount();
			}
			else {
				while(node!=null) {
					if(node.getData().equals(word)) {
						return node.getCount();
					}
					else {
						node=node.next;
					}
				}
			}
		}
		return 0;
	}
//Returns the words at the n popularity
	public String popularity(int num) {
		return popArrayList.get(num).getData();
	}
	//Sorts unique nodes as they are added
	private void sort(Node word) {
		if(popArrayList.isEmpty()) {
			popArrayList.add(word);
		}
		else {
			for(int i =0;i<popArrayList.size();i++) {
				if(popArrayList.get(i).compareTo(word)>0) {
					popArrayList.add(i, word);
					return;
				}
			}
			popArrayList.add(word);
			return;

		}
	}
private boolean isEmpty() {
	return size==0;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((word == null) ? 0 : word.hashCode());
	return result;
}

private class Node implements Comparable<Node>{
	private String data;
	private int count;
	private Node next;

	private Node(String data,Integer count) {
		this.data = data;
		this.count=count;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	private Node(Node next) {
		this.next = next;
	}
	@Override
	public int compareTo(Node n) {
		if(this.count-n.getCount()<0) {
			return 1;
		}
		else if(this.count-n.getCount()>0) {
			return -1;
		}
		else {
			return this.data.compareTo(n.getData());
		}
	}

}
}