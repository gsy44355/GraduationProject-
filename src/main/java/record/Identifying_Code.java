package record;
import java.util.HashMap;

public class Identifying_Code {

	private HashMap<String, Integer> identfyingcode;

	public Identifying_Code() {
		identfyingcode = new HashMap<String, Integer>();
		identfyingcode.put("1", 909243);
		identfyingcode.put("2", 113727);
		identfyingcode.put("3", 874709);
		identfyingcode.put("4", 461330);
		identfyingcode.put("5", 651416);
		identfyingcode.put("6", 286066);
		identfyingcode.put("7", 549237);
		identfyingcode.put("8", 930107);
		identfyingcode.put("9", 565381);
		identfyingcode.put("10", 867692);
		identfyingcode.put("11", 103061);
		identfyingcode.put("12", 465521);
		identfyingcode.put("13", 775248);
		identfyingcode.put("14", 480034);
		identfyingcode.put("15", 958328);
		identfyingcode.put("16", 788234);
		identfyingcode.put("17", 497316);
		identfyingcode.put("18", 721050);
		identfyingcode.put("19", 125786);
		identfyingcode.put("20", 677499);
		identfyingcode.put("21", 678808);
		identfyingcode.put("22", 364972);
		identfyingcode.put("23", 172950);
		identfyingcode.put("24", 731664);
		identfyingcode.put("25", 617441);
		identfyingcode.put("26", 372371);
		identfyingcode.put("27", 639356);
		identfyingcode.put("28", 331211);
		identfyingcode.put("29", 143566);
		identfyingcode.put("30", 780770);
		identfyingcode.put("31", 698027);
		identfyingcode.put("32", 600717);
		identfyingcode.put("33", 142393);
		identfyingcode.put("34", 349531);
		identfyingcode.put("35", 299717);
		identfyingcode.put("36", 717945);
		identfyingcode.put("37", 405371);
		identfyingcode.put("38", 704911);
		identfyingcode.put("39", 946492);
		identfyingcode.put("40", 405245);
		identfyingcode.put("41", 157202);
		identfyingcode.put("42", 685509);
		identfyingcode.put("43", 110536);
		identfyingcode.put("44", 767770);
		identfyingcode.put("45", 440301);
		identfyingcode.put("46", 797983);
		identfyingcode.put("47", 902821);
		identfyingcode.put("48", 264247);
		identfyingcode.put("49", 790171);
		identfyingcode.put("50", 200229);
		identfyingcode.put("51", 458511);
		identfyingcode.put("52", 798915);
		identfyingcode.put("53", 575568);
		identfyingcode.put("54", 241171);
		identfyingcode.put("55", 833974);
		identfyingcode.put("56", 755169);
		identfyingcode.put("57", 895163);
		identfyingcode.put("58", 212449);
		identfyingcode.put("59", 368105);
		identfyingcode.put("60", 210527);
		identfyingcode.put("61", 985095);
		identfyingcode.put("62", 493119);
		identfyingcode.put("63", 572711);
		identfyingcode.put("64", 724855);
		identfyingcode.put("65", 679626);
		identfyingcode.put("66", 613677);
		identfyingcode.put("67", 454850);
		identfyingcode.put("68", 475347);
		identfyingcode.put("69", 704296);
		identfyingcode.put("70", 695603);
		identfyingcode.put("71", 977041);
		identfyingcode.put("72", 641827);
		identfyingcode.put("73", 649625);
		identfyingcode.put("74", 234315);
		identfyingcode.put("75", 459442);
		identfyingcode.put("76", 662908);
		identfyingcode.put("77", 288760);
		identfyingcode.put("78", 981350);
		identfyingcode.put("79", 754309);
		identfyingcode.put("80", 797691);
		identfyingcode.put("81", 547629);
		identfyingcode.put("82", 454233);
		identfyingcode.put("83", 764565);
		identfyingcode.put("84", 603715);
		identfyingcode.put("85", 706466);
		identfyingcode.put("86", 577361);
		identfyingcode.put("87", 181318);
		identfyingcode.put("88", 208498);
		identfyingcode.put("89", 556632);
		identfyingcode.put("90", 816233);
		identfyingcode.put("91", 235293);
		identfyingcode.put("92", 377997);
		identfyingcode.put("93", 329976);
		identfyingcode.put("94", 727402);
		identfyingcode.put("95", 135834);
		identfyingcode.put("96", 530051);
		identfyingcode.put("97", 225505);
		identfyingcode.put("98", 273552);
		identfyingcode.put("99", 100311);
		identfyingcode.put("100", 971618);

	}

	public int getIdentifyingcode(String random) {
		random = random.trim();
		// Collection<Integer> a = identfyingcode.values();
		// HashSet<Integer> bHashSet = new HashSet<>();
		// for (Integer integer : a) {
		// bHashSet.add(integer);
		// }
		// System.out.println(bHashSet.size());
		return identfyingcode.get(random);
	}

	// public static void main(String[] args) {
	// System.out.println(new Identifying_Code().getIdentifyingcode("5"));
	//
	// }

}
