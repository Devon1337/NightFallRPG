package com.devon1337.RPG.Utils;

import java.util.List;

import org.bukkit.OfflinePlayer;

import com.devon1337.RPG.NightFallRPG;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class EcoSystem implements Economy {
	
	NightFallRPG plugin;
	
	public EcoSystem(NightFallRPG pl) {
        plugin = pl;
    }
	
	@Override
	public EconomyResponse bankBalance(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse bankDeposit(String arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse bankHas(String arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse bankWithdraw(String arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse createBank(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse createBank(String arg0, OfflinePlayer arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createPlayerAccount(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createPlayerAccount(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String currencyNamePlural() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String currencyNameSingular() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse deleteBank(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(String arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(String arg0, String arg1, double arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String format(double arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int fractionalDigits() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBalance(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBalance(OfflinePlayer arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBalance(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBalance(OfflinePlayer arg0, String arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getBanks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean has(String arg0, double arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean has(OfflinePlayer arg0, double arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean has(String arg0, String arg1, double arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean has(OfflinePlayer arg0, String arg1, double arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasAccount(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasAccount(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBankSupport() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EconomyResponse withdrawPlayer(String arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse withdrawPlayer(String arg0, String arg1, double arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
