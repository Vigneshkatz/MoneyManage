package com.katziio.app.util.helper;
import com.katziio.app.model.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {

    public static String TYPE = "text/csv";

    static String[] HEADERS = {"userId", "name", "email", "password", "place", "pincode", "area", "landmark", "city", "state", "phoneNumber"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<User> csvToUser(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<User> userList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                User user = new User(
                        Long.parseLong(csvRecord.get(HEADERS[0])),
                        csvRecord.get(HEADERS[1]),
                        csvRecord.get(HEADERS[2]),
                        csvRecord.get(HEADERS[3]),
                        csvRecord.get(HEADERS[4]),
                        csvRecord.get(HEADERS[5]),
                        csvRecord.get(HEADERS[6])
                );
                userList.add(user);
            }
            return userList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static void writeEmployeesToCsv(Writer writer, List<User> userList) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("userId", "name", "email", "password", "phone","accountList","ispremium","isverified");
            for (User user : userList) {
                csvPrinter.printRecord(user.getId(), user.getUserName(), user.getEmail(), user.getPassword(),user.getPhone(),
                        user.getAccountList(), user.getIsPremium(), user.getIsVerified(), user.getRoleList());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error While writing CSV ", e);
        }
    }

    public static void writeEmployeesToCsvCustom(PrintWriter writer, List<User> userList, List<String> headerList) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord(headerList.toArray());

            for (User user : userList) {
                List<String> userData = new ArrayList<>();

                for (String field : headerList) {
                    switch (field.toLowerCase()) {
                        case "userid":
                            userData.add(String.valueOf(user.getId()));
                            break;
                        case "name":
                            userData.add(user.getUserName());
                            break;
                        case "email":
                            userData.add(user.getEmail());
                            break;
                        case "phone":
                            userData.add(user.getPhone());
                            break;
                        case "isverified":
                            userData.add(user.getIsVerified()!=null?user.getIsVerified().toString():null);
                            break;
                        case "ispremium":
                            userData.add(user.getIsPremium()!=null?user.getIsPremium().toString():null);
                            break;
                        case "accountlist":
                            userData.add(user.getAccountList().toString());
                            break;
                        default:
                            userData.add("");
                            break;
                    }
                }

                csvPrinter.printRecord(userData);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while writing CSV", e);
        }
    }
}