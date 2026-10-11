package org.itsmahir.pagon.impl.features.commands;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import org.itsmahir.pagon.api.command.Command;
import org.itsmahir.pagon.api.utils.chat.ChatMessage;
import org.itsmahir.pagon.api.utils.chat.ChatUtils;

public class GrabCommand extends Command {
   public GrabCommand() {
      super("Grab Coords", "copys your coordinates to clipboard", new String[]{"grab"});
   }

   public void run(String[] args) {
      int var10000 = (int)mc.field_1724.method_23317();
      String coords = "X: " + var10000 + " Y: " + (int)mc.field_1724.method_23318() + " Z: " + (int)mc.field_1724.method_23321();
      StringSelection selection = new StringSelection(coords);
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      clipboard.setContents(selection, selection);
      ChatUtils.sendMessage(new ChatMessage("Copied Coords to clipboard", false, 0));
   }
}
