package carta.api;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
Requirements
The program you write should take in a path to a CSV file as an argument, and support an optional date parameter, in YYYY-MM-DD format.
If a date argument is provided, the summary cap table should be computed using investments made on or before the specified date.
If no date is given, calculate the cap table as of today.

The program you write should take command line arguments.

You may use any OOP programming language, libraries, or frameworks that you like, provided it is easy for us to build and run your code.

INPUT:
#INVESTMENT DATE, SHARES PURCHASED, CASH PAID, INVESTOR
2016-04-03,1000,10000.00,Sandy Lerner
2017-11-14,1000,12000.00,Don Valentine
2018-01-20,2000,40000.00,Don Valentine
2018-03-20,2000,40000.00,Ann Miura-Ko
2019-01-02,2000,50000.00,Sandy Lerner
2019-01-02,1500,13500.00,Fred Wilson

OUTPUT:
{
  "date": "02/01/2019", 	// mm/dd/yyyy format date the captable was requested
				// (if no filter is implemented, then today)
  "cash_raised": 165500.00,	// decimal representing total cash paid by
 					// investors up to date
  "total_number_of_shares": 9500,	// integer representing the total number
 						// of shares bought by investors
  "ownership": [ 	// list of investors and their aggregated investments
      {
        investor: "Don Valentine", // investor name
        shares: 3000, // total amount of shares owned by that given investor
      	  cash_paid: 52000.00, // total investment made by this investor
        ownership: 31.58 // decimal representing total investor ownership
				 // (31.58 means 31.58%)
      },
      ...
  ],
}
 */
public class Solution {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new java.text.SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println(processCapTableData("/Users/new/IdeaProjects/algoproblems/src/main/java/carta/api/sampledata.csv", ""));
    }
    public static Result processCapTableData(String srcFilePath, String dateStr) throws IOException, ParseException {
        Date date = null;
        if (!StringUtils.isBlank(dateStr)) {
            date = SIMPLE_DATE_FORMAT.parse(dateStr);
        }
        FileReader fileReader = new FileReader(srcFilePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String header = bufferedReader.readLine();
        String[] headerElements = header.split(",");
        List<ShareData> shareData = new ArrayList<>();

        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                String[] lineElements = line.split(",");
                shareData.add(new ShareData(SIMPLE_DATE_FORMAT.parse(lineElements[0]), Long.parseLong(lineElements[1]), new BigDecimal(lineElements[2]), lineElements[3]));
            } else {
                break;
            }
        }
        System.out.println(shareData);
        return summarizeData(shareData);
    }

    public static Result summarizeData(List<ShareData> shareData) {
        return null;
    }
}
