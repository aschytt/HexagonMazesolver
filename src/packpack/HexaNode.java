package packpack;

//representation of each hexagon in form of a node with HexaNode connections to each neighbor.
public class HexaNode {
	private int sum;

	private int value;

	public HexaNode l1, l2, r1, r2, top, bot;

	public HexaNode(int value) {
		this.sum = Integer.MAX_VALUE;
		this.value = value;
	}

	public boolean isBlack(){
		return value==-1;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getValue() {
		return value;
	}

}
