package CoderallyVirtual;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class SystemFailureDetective {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int logCount = scanner.nextInt();
        scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        HashMap<String, Long> warnReqs = new HashMap<>();
        Set<String> finalReqs = new TreeSet<>();
        boolean info = false;


        for (int i = 0; i < logCount; i++) {
            String logLine = scanner.nextLine();
            String[] arr = logLine.split(" ");
            String date = arr[0];
            String time = arr[1];
            String level = arr[2];
            String reqId = arr[3];
            String dateTime = date + " " + time;

            if (level.equals("WARN")) {
                LocalDateTime dateTime1 = LocalDateTime.parse(dateTime, formatter);
                long seconds = dateTime1.toEpochSecond(ZoneOffset.UTC);
                warnReqs.put(reqId, seconds);
            } else if (level.equals("INFO") || level.equals("DEBUG")) {
                LocalDateTime dateTime3 = LocalDateTime.parse(dateTime, formatter);
                long seconds = dateTime3.toEpochSecond(ZoneOffset.UTC);
                info = true;

                // if (warnReqs.containsKey(reqId)) {
                //     long timeDifference = seconds - warnReqs.get(reqId);
                //     if (timeDifference <= 2 && timeDifference > 0) {
                //         info = true;
                //     }
                // }

            } else if (level.equals("ERROR")) {

                if (warnReqs.containsKey(reqId)) {
                    LocalDateTime dateTime2 = LocalDateTime.parse(dateTime, formatter);
                    long seconds = dateTime2.toEpochSecond(ZoneOffset.UTC);
                    long timeDifference = seconds - warnReqs.get(reqId);
                    if (timeDifference <= 5 && timeDifference > 0) {
                        finalReqs.add(reqId);
                    } else if (timeDifference <= 6 && info) {
                        finalReqs.add(reqId);
                        info = false;
                    }

                }


            }
        }

        String jsonArray = finalReqs.stream()
                .map(id -> "\"" + id + "\"")
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println("json");
        System.out.println(jsonArray);

    }
}
