package events;

import main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class SuggestCommand extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date date = new Date();
        if (args[0].equalsIgnoreCase(Info.prefix + "suggest")) {
            if (args.length < 2) {
                EmbedBuilder usage = new EmbedBuilder();
                usage.setColor(Color.RED);
                usage.setTitle("\uD83D\uDD34 Invalid Usage");
                usage.setDescription("Usage: `" + Info.prefix + "suggest [suggestion]`");
                event.getChannel().sendMessage(usage.build()).queue();
            } else if (args.length == 2 && args[0].equalsIgnoreCase(Info.prefix + "suggest")) {
                if (event.getGuild().getTextChannelsByName("suggestions", true).isEmpty()) {
                    event.getGuild().createTextChannel("suggestions").queue();
                }
                else {
                    event.getMessage().delete().queue();
                    String suggestion = args[1];
                    String userName = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getUser().getName());
                    User user = event.getGuild().getMembersByName(userName, true).get(0).getUser();
                    String avatar = user.getAvatarUrl();

                    EmbedBuilder suggestionEmbed = new EmbedBuilder();

                    suggestionEmbed.setTitle("Suggestion");
                    suggestionEmbed.setColor(Color.CYAN);
                    suggestionEmbed.addField(suggestion, "", true);
                    suggestionEmbed.setFooter("From: " + Objects.requireNonNull(event.getMember()).getUser().getAsTag() + " • " + formatter.format(date), avatar);
                    event.getGuild().getTextChannelsByName("suggestions",true).get(0).sendMessage(suggestionEmbed.build()).queue(m -> {
                        m.addReaction("\u2705").queue();
                        m.addReaction("\u274C").queue();
                    });
                    event.getMessage().delete().queue();
              }
            }
            else if (args.length > 2 && args[0].equalsIgnoreCase(Info.prefix + "suggest")) {
                if (event.getGuild().getTextChannelsByName("suggestions", true).isEmpty()) {
                    event.getGuild().createTextChannel("suggestions").queue();
                }
                else {
                    event.getMessage().delete().queue();
                    String suggestion = Arrays.toString(args).replace("-suggest", "").replace("[", "").replace("]", "").replace(",", "");
                    String userName = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getUser().getName());
                    User user = event.getGuild().getMembersByName(userName, true).get(0).getUser();
                    String avatar = user.getAvatarUrl();

                    EmbedBuilder suggestionEmbed = new EmbedBuilder();

                    suggestionEmbed.setTitle("Suggestion");
                    suggestionEmbed.setColor(Color.CYAN);
                    suggestionEmbed.addField(suggestion, "", true);
                    suggestionEmbed.setFooter("From: " + Objects.requireNonNull(event.getMember()).getUser().getAsTag() + " • " + formatter.format(date), avatar);
                    event.getGuild().getTextChannelsByName("suggestions",true).get(0).sendMessage(suggestionEmbed.build()).queue(m -> {
                        m.addReaction("\u2705").queue();
                        m.addReaction("\u274C").queue();
                    });
                    event.getMessage().delete().queue();
                }
            }
        }
    }
}
