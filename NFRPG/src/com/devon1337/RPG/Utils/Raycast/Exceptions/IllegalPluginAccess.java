 package com.devon1337.RPG.Utils.Raycast.Exceptions;
 
 public class IllegalPluginAccess
   extends Exception {
   private static final long serialVersionUID = -3668815305397621275L;
   
   public IllegalPluginAccess() {}
   
   public IllegalPluginAccess(String message) {
     super(message); }
   public IllegalPluginAccess(String message, Throwable cause) { super(message, cause); } public IllegalPluginAccess(Throwable cause) {
     super(cause);
   }
 }