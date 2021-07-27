package com.devon1337.RPG.Utils;

public enum NFTType {
	Druid, Mage, GM, Raid, Object, Engine;

	public static NFTType getType(String name) {
		switch (name) {
		case "gm":
			return GM;
		case "druid":
			return Druid;
		case "mage":
			return Mage;
		case "engine":
			return Engine;
		case "object":
			return Object;
		}
		System.out.println("[NFRPG] WARNING FAILED TO IDENTIFY NFTTYPE");
		return null;
	}

	@SuppressWarnings("incomplete-switch")
	public String getName() {
		switch (this) {
		case Druid:
			return "druid";
		case Mage:
			return "mage";
		case Raid:
			return "raid";
		case GM:
			return "gm";
		case Engine:
			return "engine";
		}

		return null;

	}
}