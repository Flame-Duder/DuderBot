package main;

import events.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class BotMain {

    private static JDA jda;

    public static void main(String[] args) throws Exception{

        JDABuilder builder = new JDABuilder().createLight("ODE5NTc1NzMyOTAzNjA4MzIx.YEonYQ.SlE3LAO4R4uRyVG1KV2CvhZvpv8");
        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setLargeThreshold(50);
        builder.setActivity(Activity.watching("you"));
        builder.addEventListeners(new OnlineCount());
        builder.addEventListeners(new InfoEmbed());
        builder.addEventListeners(new ClearMessage());
        builder.addEventListeners(new SuggestCommand());
        builder.addEventListeners(new HelpCommand());
        builder.addEventListeners(new KickCommand());
        builder.addEventListeners(new ServerMessage());
        builder.addEventListeners(new PingCommand());
        builder.addEventListeners(new ModHelp());
        builder.addEventListeners(new UptimeCommand());

        JDA jda = builder.build();
    }
}