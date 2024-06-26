import java.util.Date;

public class Block {
	public String hash;
	public String previousHash;
	private String data; //simple message
	private long timeStamp; //as number of milliseconds since 1/1/1970
	private int nonce;
	
	//Block constructor
	public Block(String data, String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash(); //after we set the other values.
	}
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256(
				previousHash +
				Long.toString(timeStamp) +
				data
				);
		return calculatedhash;
	}
	public void mineBlock(int difficulty) {
		//Create a string with difficulty * "0" 
		String target = new String(new char[difficulty]).replace('\0', '0');
		while(!hash.substring(0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}

