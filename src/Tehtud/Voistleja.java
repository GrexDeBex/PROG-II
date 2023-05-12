package Tehtud;

import java.util.Comparator;

public class Voistleja{
	private final String nimi;
	private final int aeg1;
	private final int aeg2;
	private final int aeg3;
	private final int aeg4;
	private final int aeg5;
	private final int aeg6;
	private final int aegFinaal;
	private int koht1;
	private int koht2;
	private int koht3;
	private int koht4;
	private int koht5;
	private int koht6;
	private int kohtFinaal;

	public Voistleja(String nimi, int[] ajad) {
		this.nimi = nimi;
		this.aeg1 = ajad[0];
		this.aeg2 = ajad[1];
		this.aeg3 = ajad[2];
		this.aeg4 = ajad[3];
		this.aeg5 = ajad[4];
		this.aeg6 = ajad[5];
		this.aegFinaal = ajad[6];
	}

	public String getNimi() {
		return nimi;
	}

	public int getAeg1() {
		return aeg1;
	}

	public int getAeg2() {
		return aeg2;
	}

	public int getAeg3() {
		return aeg3;
	}

	public int getAeg4() {
		return aeg4;
	}

	public int getAeg5() {
		return aeg5;
	}

	public int getAeg6() {
		return aeg6;
	}

	public void setKoht1(int koht1) {
		this.koht1 = koht1;
	}

	public void setKoht2(int koht2) {
		this.koht2 = koht2;
	}

	public void setKoht3(int koht3) {
		this.koht3 = koht3;
	}

	public void setKoht4(int koht4) {
		this.koht4 = koht4;
	}

	public void setKoht5(int koht5) {
		this.koht5 = koht5;
	}

	public void setKoht6(int koht6) {
		this.koht6 = koht6;
	}

	public void setKohtFinaal(int kohtFinaal) {
		this.kohtFinaal = kohtFinaal;
	}

	public int getKohtFinaal() {
		return kohtFinaal;
	}

	@Override
	public String toString() {
		return nimi + " " + koht1 + " " + koht2 + " " + koht3 + " " +
				koht4 + " " + koht5 + " " + koht6 + " " + kohtFinaal;
	}

	static Comparator<Voistleja> aeg1Comparator() {
		return Comparator.comparingInt(voistleja -> voistleja.aeg1);
	}

	static Comparator<Voistleja> aeg2Comparator() {
		return Comparator.comparingInt(voistleja -> voistleja.aeg2);
	}

	static Comparator<Voistleja> aeg3Comparator() {
		return Comparator.comparingInt(voistleja -> voistleja.aeg3);
	}

	static Comparator<Voistleja> aeg4Comparator() {
		return Comparator.comparingInt(voistleja -> voistleja.aeg4);
	}

	static Comparator<Voistleja> aeg5Comparator() {
		return Comparator.comparingInt(voistleja -> voistleja.aeg5);
	}

	static Comparator<Voistleja> aeg6Comparator() {
		return Comparator.comparingInt(voistleja -> voistleja.aeg6);
	}

	static Comparator<Voistleja> aegFinaalComparator() {
		return Comparator.comparingInt(voistleja -> voistleja.aegFinaal);
	}

	public int[] koikKohad(){
		return new int[]{koht1, koht2, koht3, koht4, koht5, koht6, kohtFinaal};
	}

}
