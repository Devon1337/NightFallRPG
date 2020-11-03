 package com.devon1337.RPG.PassiveAbilities;
 
 import org.bukkit.entity.Player;
 import org.bukkit.potion.PotionEffect;
 import org.bukkit.potion.PotionEffectType;
 
 
 public class FluidCasting
 {
   public void doSelfHeal(Player player) {
     player.setHealth(player.getHealth() + 3.0D);
   }
   
   public void doJumpBoost(Player player) {
     player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 3));
   }
 
   
   public void doSwiftness(Player player) {
     player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 3));
   }
   
   public void doLevitation(Player player) {
     player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 160, 2));
   }
   
   public void doCurse(Player player) {
     player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 2));
   }
 }