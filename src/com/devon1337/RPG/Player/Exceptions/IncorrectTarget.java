 package com.devon1337.RPG.Player.Exceptions;
 
 public class IncorrectTarget
   extends Exception {
   private static final long serialVersionUID = -8095623457164253876L;
   
   public IncorrectTarget() {}
   
   public IncorrectTarget(String message) {
     super(message); }
   public IncorrectTarget(String message, Throwable cause) { super(message, cause); } public IncorrectTarget(Throwable cause) {
     super(cause);
   }
 }