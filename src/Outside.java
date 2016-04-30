/**
 * Created by dustinrosebery on 4/10/16.
 */
public class Outside extends Rooms {

   Outside(){
       roomIndex = 0;

       name = ("Outside");

       //Saves.loadDescription(name);
        description = Saves.loadDescription(name);

       /*
       description = ("With a slight shudder of anticipation, and an almost imperceptible twitch, you scan your surroundings." +
               "Beautiful houses with long driveways and very well kept lawns line the twisting street ahead. Cars that you" +
               "have only seen on tv are parked in front. You wonder how you even got in here... Wasn't there a gate somewhere?" +
               " Oh well, you shrug, \"I guess it's time to get to work.\" Welcome to..." +
               "\n" +
               "\nThe Night Shift" +
               "\n" +
               "\nHere it is, the perfect one. Not the biggest, not the smallest, but the nicest by far. The grass is so well kept you think" +
               "the biggest crime here would be to mess it up by walking on it, but then you remember the business at hand. You see two " +
               "obvious points of entry, the front door, and what looks like the bedroom window after taking a look from the shadows around" +
               "the house. What would you like to do?");*/

   };


    /**
     * runs whenever a character enters the room
      * @param character
     */
   public void entry(Character character) {
       character.setIndex(roomIndex);
       int state = 0;

       Game.getController().updateRoom(character);
   }
}
