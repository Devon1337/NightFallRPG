 package com.devon1337.RPG.raid;
 
 public enum NFRaid
 {
   Crystal_Heist;
	 
   public static NFRaid getString(String type) {
     String str;
     switch ((str = type).hashCode()) { case 2033480676: if (!str.equals("Crystal_Heist"))
           break; 
         return Crystal_Heist; }
     
     return null;
   }
 }