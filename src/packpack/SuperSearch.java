package packpack;

import java.util.*;

//This class calculates the minimum sum to all neighboring Nodes away from the starting Node.
public class SuperSearch {
	
	//The queue is used to make sure that no lower level Nodes are expanded before all high level Nodes has been expanded.
	//That is, if there's two Nodes in the queue, the neighboring Nodes of the first Node popped will not be further expanded until the second Node is expanded.
	//This ensures that we always get the lowest sum, and it doesn't waste expansion time on Nodes
	private Queue<HexaNode> queue;
	
	public SuperSearch(HexaNode startNode){
		queue=new LinkedList<HexaNode>();
		startNode.setSum(startNode.getValue());
		queue.add(startNode);
		
		while (!queue.isEmpty()){
			HexaNode temp=queue.poll();
			if(temp.l1!=null && !temp.l1.isBlack()){				
				if(temp.getSum()+temp.l1.getValue() < temp.l1.getSum()){
					temp.l1.setSum(temp.getSum()+temp.l1.getValue());
					queue.add(temp.l1);
				}
			}
			if(temp.l2!=null && !temp.l2.isBlack()){				
				if(temp.getSum()+temp.l2.getValue() < temp.l2.getSum()){
					temp.l2.setSum(temp.getSum()+temp.l2.getValue());
					queue.add(temp.l2);
				}
			}
			if(temp.r1!=null && !temp.r1.isBlack()){				
				if(temp.getSum()+temp.r1.getValue() < temp.r1.getSum()){
					temp.r1.setSum(temp.getSum()+temp.r1.getValue());
					queue.add(temp.r1);
				}
			}
			if(temp.r2!=null && !temp.r2.isBlack()){				
				if(temp.getSum()+temp.r2.getValue() < temp.r2.getSum()){
					temp.r2.setSum(temp.getSum()+temp.r2.getValue());
					queue.add(temp.r2);
				}
			}
			if(temp.top!=null && !temp.top.isBlack()){				
				if(temp.getSum()+temp.top.getValue() < temp.top.getSum()){
					temp.top.setSum(temp.getSum()+temp.top.getValue());
					queue.add(temp.top);
				}
			}
			if(temp.bot!=null && !temp.bot.isBlack()){				
				if(temp.getSum()+temp.bot.getValue() < temp.bot.getSum()){
					temp.bot.setSum(temp.getSum()+temp.bot.getValue());
					queue.add(temp.bot);
				}
			}
		}
	}  

}
