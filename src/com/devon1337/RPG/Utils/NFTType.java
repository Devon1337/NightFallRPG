 package com.devon1337.RPG.Utils;
 
 public enum NFTType {
   Druid, Mage, GM, Raid, Object;
	 
   public static NFTType getType(String name) {
     String str;
     switch ((str = name).hashCode()) { case -1023368385: if (!str.equals("object")) {
           break;
         }
         return Object;case 3302: if (!str.equals("gm")) break;  return GM;case 3343730: if (!str.equals("mage")) break;  return Mage;case 3492746: if (!str.equals("raid"))
           break;  return Raid;case 95864066: if (!str.equals("druid"))
           break;  return Druid; }  return null;
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
	   }
	   
	   return null;
   
   }
 }