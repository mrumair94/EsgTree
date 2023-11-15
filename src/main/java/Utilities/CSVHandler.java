package Utilities;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {
    private String csvFilePath;
    private String lastProcessedEmail = null;
    public CSVHandler(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public void writeData(String companyName, String emailId) {
        File file = new File(csvFilePath);
        boolean fileExists = file.exists();

        try (FileWriter writer = new FileWriter(csvFilePath, true);
             CSVWriter csvWriter = new CSVWriter(writer)) {

            if (!fileExists) {
                // Add header if the file is empty or does not exist
                csvWriter.writeNext(new String[]{"Company name", "Email ID", "Status"});
            }

            // Add new data with "Pending" status
            csvWriter.writeNext(new String[]{companyName, emailId, "Pending"});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getCompanyNameWithPendingStatus() {
        String[] nextRecord = null;

        try (Reader reader = new FileReader(csvFilePath);
             CSVReader csvReader = new CSVReaderBuilder(reader)
                     .withSkipLines(1) // Skip the header row
                     .build()) {

            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord.length >= 3 && "Pending".equals(nextRecord[2])) {
                    //System.out.println("newly added company: "+nextRecord[0]);
                    return nextRecord[0];
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getEmailWithApprovedStatus() {
        String[] nextRecord = null;
        try (Reader reader = new FileReader(csvFilePath);
             CSVReader csvReader = new CSVReaderBuilder(reader)
                     .withSkipLines(1 ) // Skip the header row
                     .build()) {

            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord.length >= 3 && "Approved".equals(nextRecord[2])) {
                   // System.out.println("Approved company email: "+nextRecord[1]);
                    return nextRecord[1];
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return null;
    }
    public String getNextApprovedEmail() {
        String[] nextRecord = null;
        try (Reader reader = new FileReader(csvFilePath);
             CSVReader csvReader = new CSVReaderBuilder(reader)
                     .withSkipLines(1) // Skip the header row
                     .build()) {

            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord.length >= 3 && "Approved".equals(nextRecord[2])) {
                    String email = nextRecord[1];
                    if (lastProcessedEmail == null || !email.equals(lastProcessedEmail)) {
                        lastProcessedEmail = email;
                        return email;
                    }
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<String[]> getApprovedEmails() {
        List<String[]> approvedEmails = new ArrayList<>();
        String[] nextRecord = null;
        try (Reader reader = new FileReader(csvFilePath);
             CSVReader csvReader = new CSVReaderBuilder(reader)
                     .withSkipLines(1) // Skip the header row
                     .build()) {

            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord.length >= 3 && "Approved".equals(nextRecord[2])) {
                    String email = nextRecord[1];
                    approvedEmails.add(nextRecord);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return approvedEmails;
    }

    public String updateStatus(String companyName, String newStatus) {
        List<String[]> records = new ArrayList<>();
        String updatedStatus = null;

        try (Reader reader = new FileReader(csvFilePath);
             CSVReader csvReader = new CSVReader(reader)) {

            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord.length >= 3 && companyName.equals(nextRecord[0])) {
                    nextRecord[2] = newStatus;
                    updatedStatus = newStatus;
                }
                records.add(nextRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }

        if (updatedStatus != null) {
            try (Writer writer = new FileWriter(csvFilePath);
                 CSVWriter csvWriter = new CSVWriter(writer)) {

                // Write the updated records back to the CSV file
                for (String[] record : records) {
                    csvWriter.writeNext(record);
                  //  System.out.println("approved status"+record);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return updatedStatus;
    }
    public boolean deleteRowByCompanyName(String companyName) {
        List<String[]> records = new ArrayList<>();
        boolean rowDeleted = false;

        try (Reader reader = new FileReader(csvFilePath);
             CSVReader csvReader = new CSVReader(reader)) {

            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord.length >= 3 && companyName.equals(nextRecord[0])) {
                    // If the company name matches, skip this record (effectively deleting the row)
                    rowDeleted = true;
                } else {
                    records.add(nextRecord);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        if (rowDeleted) {
            try (Writer writer = new FileWriter(csvFilePath);
                 CSVWriter csvWriter = new CSVWriter(writer)) {
                // Write the remaining records back to the CSV file, excluding the deleted row
                for (String[] record : records) {
                    csvWriter.writeNext(record);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return rowDeleted;
    }
    public List<String> getCompanyNamesByStatus(String status) {
        List<String> companyNames = new ArrayList<>();

        try (Reader reader = new FileReader(csvFilePath);
             CSVReader csvReader = new CSVReader(reader)) {

            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord.length >= 3 && status.equals(nextRecord[2])) {
                    companyNames.add(nextRecord[0]); // Assuming company name is in the first column
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }

        return companyNames;
    }


}
