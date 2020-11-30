package main;

import auxiliaryentities.Action;
import auxiliarylists.ActorList;
import auxiliarylists.MovieList;
import auxiliarylists.SerialList;
import auxiliarylists.UserList;
import auxiliarylists.ActionList;
import checker.Checkstyle;
import checker.Checker;
import common.Constants;
import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();
        // actors, actions, users, movies, serials

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();

        //TODO add here the entry point to your implementation

        /**
         * We initialise the lists of input entities : actors, movies, serials, users and actions
         * as entities that we have created
         */

        ActorList listofActors = new ActorList();
        listofActors.getInputActors(input.getActors());

        MovieList listofMovies = new MovieList();
        listofMovies.getInputMovies(input.getMovies());

        UserList listofUsers = new UserList();
        listofUsers.getInputUsers(input.getUsers());

        SerialList listofSerials = new SerialList();
        listofSerials.getInputSerials(input.getSerials());

        ActionList listofActions = new ActionList();
        listofActions.getInputActions(input.getCommands(), listofUsers, listofMovies,
                listofActors, listofSerials);

        /**
         * A loop that parses all of the actions that are required
         */
        for (int i = 0; i < listofActions.getSize(); i++) {
            Action cmd = listofActions.getAction(i);

            JSONObject object = fileWriter.writeFile(cmd.getActionId(), "", cmd.checkActionType());
            arrayResult.add(object);

        }
        fileWriter.closeJSON(arrayResult);
    }
}
