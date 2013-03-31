package nl.gogognome.katacoinchange;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoinChangeTest {

	@Test
	public void noCoinsAndAmount0ShouldSucceed() {
		CoinChange cc = new CoinChange(new int[0], 0);
		assertArrayEquals(new int[0], cc.getCointCount());
	}

	@Test
	public void oneCoinAndAmount0ShouldReturnZeroCoins() {
		CoinChange cc = new CoinChange(new int[] { 1 }, 0);
		assertEquals(0, cc.f[0][0]);
		assertArrayEquals(new int[] { 0 }, cc.getCointCount());
	}

	@Test
	public void oneCoinOf1AndAmount1ShouldReturnOneCoin() {
		CoinChange cc = new CoinChange(new int[] { 1 }, 1);
		assertEquals(1, cc.f[0][1]);
		assertArrayEquals(new int[] { 1 }, cc.getCointCount());
	}

	@Test
	public void oneCoinOf1AndAmount2ShouldReturnTwoCoins() {
		CoinChange cc = new CoinChange(new int[] { 1 }, 2);
		assertEquals(2, cc.f[0][2]);
		assertArrayEquals(new int[] { 2 }, cc.getCointCount());
	}

	@Test
	public void oneCoinOf1AndOneCoinOf5AndAmount6ShouldReturnTwoCoins() {
		CoinChange cc = new CoinChange(new int[] { 1, 5 }, 6);
		assertEquals(2, cc.f[1][2]);
		assertArrayEquals(new int[] { 1, 1 }, cc.getCointCount());
	}

	@Test
	public void oneCoinOf2AndAmount1ShouldNotBePossible() {
		CoinChange cc = new CoinChange(new int[] { 2 }, 1);
		assertEquals(CoinChange.INFINITY, cc.f[0][1]);
		assertNull(cc.getCointCount());
	}

	@Test
	public void coins_1_5_10_25_100_amount_11_returnsTwoCoins() {
		CoinChange cc = new CoinChange(new int[] { 1, 5, 10, 25, 100 }, 11);
		assertArrayEquals(new int[] { 1, 0, 1, 0, 0 }, cc.getCointCount());
	}

	@Test
	public void coins_1_5_10_25_100_amount_15_returnsTwoCoins() {
		CoinChange cc = new CoinChange(new int[] { 1, 5, 10, 25, 100 }, 15);
		assertArrayEquals(new int[] { 0, 1, 1, 0, 0 }, cc.getCointCount());
	}

	@Test
	public void coins_1_5_10_25_100_amount_40_returnsThreeCoins() {
		CoinChange cc = new CoinChange(new int[] { 1, 5, 10, 25, 100 }, 40);
		assertArrayEquals(new int[] { 0, 1, 1, 1, 0 }, cc.getCointCount());
	}

	@Test
	public void coins_1_20_50_amount_60_returnsThreeCoins() {
		CoinChange cc = new CoinChange(new int[] { 1, 20, 50 }, 60);
		assertArrayEquals(new int[] { 0, 3, 0 }, cc.getCointCount());
	}

	@Test
	public void coins_1_3_4_amount_6_returnsTwoCoins() {
		CoinChange cc = new CoinChange(new int[] { 1, 3, 4 }, 6);
		assertArrayEquals(new int[] { 0, 2, 0 }, cc.getCointCount());
	}
}
