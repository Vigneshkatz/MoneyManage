package com.katziio.app.util.helper;

import com.katziio.app.model.Account;
import com.katziio.app.model.Expense;
import com.katziio.app.model.User;
import com.katziio.app.service.account.AccountService;
import com.katziio.app.service.account.AccountServiceImpl;
import com.katziio.app.service.user.UserService;
import com.katziio.app.service.user.UserServiceImpl;
import com.katziio.app.util.CustomUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {

    private static UserServiceImpl userService = null;
    private static AccountService accountService = null;

    @Autowired
    public CSVHelper() {
        userService = new UserServiceImpl();
        accountService = new AccountServiceImpl();
    }

    private static final String TYPE = "text/csv";
    private static final String[] ACCOUNT_HEADER = {"user_Id", "accountNumber", "currentBalance", "cardNumber", "cvv", "bankName", "ifsc", "phoneLinked", "isNetBanking", "isActive", "createdAt", "accountType", "accountMonthlySpendLimit", "monthlySpent"};
    private static final String[] EXPENSE_HEADER = {"id", "time", "amountSpent", "initialBalance", "updatedBalance", "category", "spentOn", "account_id"};
    private static final String[] USER_HEADERS = {"id", "name", "email", "password", "place", "phone", "isPremium", "isVerified"};

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
                        Long.parseLong(csvRecord.get(USER_HEADERS[0])),
                        csvRecord.get(USER_HEADERS[1]),
                        csvRecord.get(USER_HEADERS[2]),
                        csvRecord.get(USER_HEADERS[3]),
                        csvRecord.get(USER_HEADERS[4]),
                        csvRecord.get(USER_HEADERS[5]),
                        csvRecord.get(USER_HEADERS[6])
                );
                userList.add(user);
            }
            return userList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static void writeUserToCsv(Writer writer, List<User> userList) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("userId", "name", "email", "password", "phone", "accountList", "isPremium", "isVerified");
            for (User user : userList) {
                csvPrinter.printRecord(user.getId(), user.getUserName(), user.getEmail(), user.getPassword(), user.getPhone(),
                        user.getAccountList(), user.getIsPremium(), user.getIsVerified(), user.getRoleList());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error While writing CSV ", e);
        }
    }

    public static void writeUserToCsvCustom(PrintWriter writer, List<User> userList, List<String> headerList) {
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
                            userData.add(user.getIsVerified() != null ? user.getIsVerified().toString() : null);
                            break;
                        case "ispremium":
                            userData.add(user.getIsPremium() != null ? user.getIsPremium().toString() : null);
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

    public static List<Account> csvToAccount(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Account> accountList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();


            for (CSVRecord csvRecord : csvRecords) {
                if (!CustomUtil.isValidObject(csvRecord.get(ACCOUNT_HEADER[0]) != null) && !userService.isValidUser(Long.parseLong(csvRecord.get(ACCOUNT_HEADER[0])))) {
                    throw new RuntimeException();
                }
                User user = userService.getAccountById(Long.parseLong(csvRecord.get(ACCOUNT_HEADER[0])));

                Account account = new Account(
                        user,
                        csvRecord.get(ACCOUNT_HEADER[1]),
                        csvRecord.get(ACCOUNT_HEADER[2]),
                        csvRecord.get(ACCOUNT_HEADER[3]),
                        csvRecord.get(ACCOUNT_HEADER[4]),
                        csvRecord.get(ACCOUNT_HEADER[5]),
                        csvRecord.get(ACCOUNT_HEADER[6]),
                        csvRecord.get(ACCOUNT_HEADER[7]),
                        csvRecord.get(ACCOUNT_HEADER[8]),
                        csvRecord.get(ACCOUNT_HEADER[9]),
                        csvRecord.get(ACCOUNT_HEADER[10]),
                        csvRecord.get(ACCOUNT_HEADER[11]),
                        csvRecord.get(ACCOUNT_HEADER[12]),
                        csvRecord.get(ACCOUNT_HEADER[13])
                );
                accountList.add(account);
            }
            return accountList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static void writeAccountToCsv(Writer writer, List<Account> accounList) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("account_id", "user_Id", "accountNumber", "currentBalance", "cardNumber", "cvv", "bankName", "ifsc", "phoneLinked", "isNetBanking", "isActive", "createdAt", "accountType", "accountMonthlySpendLimit", "monthlySpent");
            for (Account account : accounList) {
                csvPrinter.printRecord(account.getId(), account.getUser().getId(), account.getAccountNumber(), account.getCurrentBalance(), account.getCardNumber(), account.getCvv(),
                        account.getBankName(), account.getIfsc(), account.getPhoneLinked(), account.getIsNetBanking(), account.getIsActive(), account.getCreatedAt(), account.getAccountType(), account.getAccountMonthlySpendLimit(), account.getMonthlySpent());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error While writing CSV ", e);
        }
    }

    public static List<Expense> csvToExpense(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Expense> expenseList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                if (!CustomUtil.isValidObject(csvRecord.get(EXPENSE_HEADER[7]) != null) && !accountService.isValidAccount(Long.parseLong(csvRecord.get(EXPENSE_HEADER[7])))) {
                    throw new RuntimeException();
                }
                Account account = accountService.getAccountById(Long.valueOf(csvRecord.get(EXPENSE_HEADER[7])));
                Expense expense = new Expense(
                        csvRecord.get(EXPENSE_HEADER[1]),
                        Long.parseLong(csvRecord.get(EXPENSE_HEADER[2])),
                        CustomUtil.getCategory(csvRecord.get(EXPENSE_HEADER[3])),
                        Long.parseLong(csvRecord.get(EXPENSE_HEADER[4])),
                        account,
                        Long.parseLong(csvRecord.get(EXPENSE_HEADER[6]))
                );
                expenseList.add(expense);
            }
            return expenseList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static void writeExpenseToCsv(Writer writer, List<Expense> expenseList) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("id", "time", "amountSpent", "initialBalance", "updatedBalance", "category", "spentOn", "account_id");
            for (Expense expense : expenseList) {
                csvPrinter.printRecord(expense.getId(), expense.getTime(), expense.getAmountSpent(), expense.getUpdatedBalance(), expense.getCategory(),
                        expense.getSpentOn(), expense.getAccount().getId());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error While writing CSV ", e);
        }
    }
}