package packpack;

import java.io.*;
import java.util.*;

public class HexaTable {
	private ArrayList<HexaNode> table;
	
	public HexaTable() throws IOException {
		this.table = new ArrayList<HexaNode>();
	}
	//Simply reads in the file and adds each new hexagon as a HexaNode to the list. After all hexagons has been read into the list, 
	//goes through each Node in the list and connect to each relevant neightbor Node.
	public void load(String filename) {

		String line;

		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			line = in.readLine();
			
				while (line != null && line.length() > 1) {
				String x[] = line.split(" ");
				//a=key b=value
				int a = Integer.parseInt(x[0]) - 1;
				int b = Integer.parseInt(x[1]);
				table.add(a, new HexaNode(b));

				line = in.readLine();
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// setting up nodes in the list. Each neightbor is X distance away in the list.
		for(int i=0; i<=232; i++){
			if(inRange(i-8)){table.get(i).l1=table.get(i-8);}
			if(inRange(i+7)){table.get(i).l2=table.get(i+7);}
			if(inRange(i-7)){table.get(i).r1=table.get(i-7);}
			if(inRange(i+8)){table.get(i).r2=table.get(i+8);}
			if(inRange(i+15)){table.get(i).bot=table.get(i+15);}
			if(inRange(i-15)){table.get(i).top=table.get(i-15);}
		}
		// releases all far-left column nodes with connections that shouldn't be connected.
		for (int i = 0; i <= 232; i += 15) {
			if (i == 0) {
				table.get(i).r1 = null;
			} else if (i == 225) {
				table.get(i).r2 = null;
			}
			table.get(i).l1 = null;
			table.get(i).l2 = null;
		}
		// releases all far-right column nodes with connections that shouldn't be connected.
		for (int i = 7; i <= 232; i += 15) {
			if (i == 7) {
				table.get(i).l1 = null;
			} else if (i == 232) {
				table.get(i).l2 = null;
			}
			table.get(i).r1 = null;
			table.get(i).r2 = null;
		}

	}
	
	public void printSolution() throws IOException{
		
		//Creates a super search that calculates the smallest sum to each and every Node from starting position 225 in the list.
		SuperSearch superSearch=new SuperSearch(table.get(225));
		BufferedWriter out= new BufferedWriter(new FileWriter("out.txt"));
		Stack<HexaNode> stack = new Stack<HexaNode>();
		HexaNode temp=table.get(7);
		HexaNode toAdd=null;

		int currentSum=table.get(7).getSum();
		int MINCOST=currentSum;
		
		//Pushes the finishing Node onto a stack and performs Node traversals until we finally hit our starting Node again.
		//This sequence chooses the neighboring Node with the smallest sum, then adds that one to the stack, and updates "temp" to that Node until we are at start Node.
		stack.push(temp);
		
		if(MINCOST!=Integer.MAX_VALUE){
			
		while(table.indexOf(temp)!=225){
			
			//Neighboring Nodes must exist and must not be a black Node before being considered to be lowest sum.
			if(temp.l1!=null && !temp.l1.isBlack() && temp.l1.getSum()<currentSum){
				currentSum=temp.l1.getSum();
				toAdd=temp.l1;	
			}
			if(temp.l2!=null && !temp.l2.isBlack() && temp.l2.getSum()<currentSum){
				currentSum=temp.l2.getSum();
				toAdd=temp.l2;	
			}
			if(temp.r1!=null && !temp.r1.isBlack() && temp.r1.getSum()<currentSum){
				currentSum=temp.r1.getSum();
				toAdd=temp.r1;	
			}
			if(temp.r2!=null && !temp.r2.isBlack() && temp.r2.getSum()<currentSum){
				currentSum=temp.r2.getSum();
				toAdd=temp.r2;	
			}
			if(temp.top!=null && !temp.top.isBlack() && temp.top.getSum()<currentSum){
				currentSum=temp.top.getSum();
				toAdd=temp.top;	
			}
			if(temp.bot!=null && !temp.bot.isBlack() && temp.bot.getSum()<currentSum){
				currentSum=temp.bot.getSum();
				toAdd=temp.bot;	
			}
			
			//toAdd repreresents the neightbouring node with the smallest sum, this one is then added to the stack in order to show the "path" from finish to start.
			stack.push(toAdd);
			//Temp is then updated to be that Node.
			temp=toAdd;	
		}
		//Finally prints each Node that was added to the stack in reversed order (according to assignment specification).
		//visualizes the path taken.
		while(!stack.isEmpty()){
			
			try {
				out.write((int)(table.indexOf(stack.pop())+1)+"\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
				out.write("MINIMAL-COST PATH COSTS: "+MINCOST);
		
		}
		else{
			out.write("NO SOLUTION");
			System.out.println("NO SOLUTION");
		}
		out.close();
	}

	//Used for debugging.
	public void printAll() {
		for (HexaNode each : table) {
			System.out.println(table.indexOf(each) + " " + each.getValue());
		}
	}


	public boolean inRange(int index) {
		return ((0 <= index) && (index <= 232));
	}
}
