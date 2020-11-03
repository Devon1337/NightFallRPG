 package com.devon1337.RPG.Quests.Exceptions;
 
 public class QuestIDInUse
   extends Exception {
   private static final long serialVersionUID = -5147446993071623733L;
   
   public QuestIDInUse() {}
   
   public QuestIDInUse(String message) {
     super(message); }
   public QuestIDInUse(String message, Throwable cause) { super(message, cause); } public QuestIDInUse(Throwable cause) {
     super(cause);
   }
 }