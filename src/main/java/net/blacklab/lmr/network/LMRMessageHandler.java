package net.blacklab.lmr.network;

import mmmlibx.lib.MMMLib;
import net.blacklab.lmr.LittleMaidReengaged;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LMRMessageHandler implements IMessageHandler<LMRMessage, IMessage>
{
	@Override//IMessageHandlerのメソッド
	public IMessage onMessage(LMRMessage message, MessageContext ctx)
	{
		if(message.data != null)
		{
			if(ctx.side.isClient())
			{
				LittleMaidReengaged.proxy.clientCustomPayload(message);
			}
			else
			{
				if(message.ch == 1)
				{
					MMMLib.serverCustomPayload(ctx.getServerHandler().playerEntity, message);
				}
				if(message.ch == 2)
				{
					NetworkSync.serverCustomPayload(ctx.getServerHandler().playerEntity, message);
				}
			}
		}
		return null;//本来は返答用IMessageインスタンスを返すのだが、旧来のパケットの使い方をするなら必要ない。
	}
}