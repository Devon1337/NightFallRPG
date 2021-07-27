 package com.devon1337.RPG.Utils.Raycast.Exceptions;
 
 public class BadProjectile
   extends Exception {
   private static final long serialVersionUID = 1086183713010162964L;
   
   public BadProjectile() {}
   
   public BadProjectile(String message) {
     super(message); }
  public BadProjectile(String message, Throwable cause) { super(message, cause); } public BadProjectile(Throwable cause) {
     super(cause);
   }
 }