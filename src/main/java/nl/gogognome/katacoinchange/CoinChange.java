package nl.gogognome.katacoinchange;

public class CoinChange {

	private final int[] coins;
	private final int amount;
	final int[][] f;

	final static int INFINITY = Integer.MAX_VALUE;

	public CoinChange(int[] coins, int amount) {
		this.coins = coins;
		this.amount = amount;
		f = new int[coins.length][];

		initF();
	}

	public void initF() {
		for (int c=0; c<coins.length; c++) {
			f[c] = new int[amount+1];
			for (int a=0; a<=amount; a++) {
				int value = INFINITY;
				if (a == 0) {
					value = 0;
				}
				if (a >= coins[c] && f[c][a-coins[c]] != INFINITY) {
					value = Math.min(value, f[c][a-coins[c]]) + 1;
				}
				if (c >= 1) {
					value = Math.min(value, f[c-1][a]);
				}
				f[c][a] = value;
			}
		}
	}

	public int[] getCointCount() {
		if (f.length == 0) {
			return new int[0];
		}

		int[] coinCount = new int[f.length];
		int c = f.length - 1;
		int a = f[0].length - 1;
		if (f[c][a] == INFINITY) {
			return null;
		}

		while (a > 0 && c >= 0) {
			if (a >= coins[c] && f[c][a-coins[c]]+1 == f[c][a]) {
				a -= coins[c];
				coinCount[c]++;
			}
			if (c > 0 && f[c-1][a] == f[c][a]) {
				c--;
			}
		}

		return coinCount;
	}
}
