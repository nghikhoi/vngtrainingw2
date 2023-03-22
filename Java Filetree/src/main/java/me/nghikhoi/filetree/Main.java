package me.nghikhoi.filetree;

import me.nghikhoi.filetree.InstrumentationAgent;
import me.nghikhoi.filetree.reader.FileSystemReader;
import me.nghikhoi.filetree.reader.YamlFileSystemReader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void printObjectSize(Object object) {
        System.out.println("Object type: " + object.getClass() +
                ", size: " + InstrumentationAgent.getObjectSize(object) + " bytes");
    }

    public static void main(String[] args) {
        java.io.File file = new java.io.File("src/main/resources/tree.yml");
        FileSystemReader reader = new YamlFileSystemReader(file);
        FileSystem fileSystem = new FileSystem(reader);

        me.nghikhoi.filetree.File root = fileSystem.getRoot();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();

            int index = command.indexOf(' ');
            String cmdLabel = "";
            String cmdArgs = "";
            if (index != -1) {
                cmdLabel = command.substring(0, index);
                cmdArgs = command.substring(index + 1);
            } else {
                cmdLabel = command;
            }
            cmdLabel = cmdLabel.trim().toLowerCase();
            cmdArgs = cmdArgs.trim().toLowerCase();

            switch (cmdLabel) {
                case "ls":
                    for (String fileName : root.listFiles()) {
                        System.out.println(fileName);
                    }
                    break;
                case "cd":
                    if (cmdArgs.equals("..")) {
                        root = fileSystem.newFile(root.getParent());
                    } else {
                        root = fileSystem.newFile(root.getPath() + "/" + cmdArgs);
                    }
                    break;
                case "find":
                    if (cmdArgs.equals("")) {
                        System.out.println("Please enter a file name");
                    } else {
                        LinkedList<File> queue = new LinkedList<>();
                        queue.add(root);

                        List<String> result = new ArrayList<>();

                        while (!queue.isEmpty()) {
                            File current = queue.poll();
                            if (current.getName().equals(cmdArgs)) {
                                result.add(current.getPath());
                            }
                            if (current.isDirectory()) {
                                for (String filePath : current.listFiles()) {
                                    queue.add(fileSystem.newFile(filePath));
                                }
                            }
                        }

                        if (result.isEmpty()) {
                            System.out.println("No file found");
                        } else {
                            for (String path : result) {
                                System.out.println(path);
                            }
                        }
                    }
                    break;
                case "pwd":
                    System.out.println(root.getPath());
                    break;
                case "size":
                    System.out.println(root.diskSize());
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Unknown command");
            }
        }
    }

}
