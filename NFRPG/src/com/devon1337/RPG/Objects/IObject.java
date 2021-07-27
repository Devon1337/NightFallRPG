package com.devon1337.RPG.Objects;

import com.devon1337.RPG.Player.NFPlayer;

public interface IObject {
	public void onLeftClick(NFPlayer player);
	public void onRightClick(NFPlayer player);
	public void onHover(NFPlayer player);
}
