package com.abyat.mvp;

import com.abyat.mvp.interfaces.IPlayerMatchStats;
import com.abyat.mvp.model.Basketball;
import com.abyat.mvp.model.Handball;
import com.abyat.mvp.util.AbyatUtility;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class AbyatMain {

    public static final Map<String, Class> GAMES = new HashMap<>();

    // add implementations to support more sports
    static {
        GAMES.put("BASKETBALL", Basketball.class);
        GAMES.put("HANDBALL", Handball.class);
    }

    public static void main(String[] args) throws Exception {

        List<IPlayerMatchStats> iPlayerMatchStats = new ArrayList<>();
        try (Stream<Path> filePathStream = Files.walk(Paths.get("E:\\IdeaProjects\\abyat-mvp\\sample_data"))) {
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    System.out.println(filePath);

                    File file = filePath.toFile();
                    try {
                        iPlayerMatchStats.addAll(AbyatUtility.readMatchStats(new FileInputStream(file)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        // print Most valuable player
        System.out.println( "Most valuable player " + AbyatUtility.getMVPNickName(iPlayerMatchStats));
    }
}
