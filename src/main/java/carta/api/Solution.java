package carta.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
Solution for processing cap table data
 */
public class Solution {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new java.text.SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println(processCapTableData(args.length > 0 ? args[0] : "/Users/new/IdeaProjects/algoproblems/sample.csv", args.length > 1 ? args[1] : ""));
    }
    public static String processCapTableData(String srcFilePath, String dateStr) throws IOException, ParseException {
        Date date = new Date(); // initialize date to current date
        if (!StringUtils.isBlank(dateStr)) {
            // override to provided date
            date = SIMPLE_DATE_FORMAT.parse(dateStr);
        }
        FileReader fileReader = new FileReader(srcFilePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String header = bufferedReader.readLine();
        String[] headerElements = header.split(",");
        List<ShareData> shareData = new ArrayList<>();
        int lineCount = 2; //starts at 1 (which we ignore as header elements)
        Long totalShares = 0L;
        Double cashRaised = 0.00;
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                String[] lineElements = line.split(",");
                if (lineElements.length  < 4) {
                    System.out.println("Ignoring invalid data on line #"+lineCount);
                    lineCount++;
                    continue;
                }
                Date investmentDate = SIMPLE_DATE_FORMAT.parse(lineElements[0]);
                if (investmentDate.after(date)) {
                    continue; // ignore data points before after given date
                }
                Long sharesPurchased = Long.parseLong(lineElements[1]);
                Double cashPaid =Double.parseDouble(lineElements[2]);
                String investor = lineElements[3];
                shareData.add(new ShareData(investmentDate, sharesPurchased, cashPaid, investor));

                // update summary stats
                cashRaised += cashPaid;
                totalShares += sharesPurchased;
                lineCount++;
            } else {
                break;
            }
        }
        Result result = summarizeData(shareData, date, totalShares, cashRaised);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(result);
    }

    public static Result summarizeData(List<ShareData> shareDataList, Date date, Long totalShares, Double cashRaised) {
        String formattedDate = SIMPLE_DATE_FORMAT.format(date);

        List<ShareOwner> shareOwnerList = new ArrayList<>();
        for (ShareData data: shareDataList) {
            ShareOwner shareOwner = new ShareOwner(data.getInvestor(), data.getSharesPurchased(), data.getCashPaid(), (new BigDecimal(data.getSharesPurchased()).divide(new BigDecimal(totalShares),2, RoundingMode.CEILING)));
            shareOwnerList.add(shareOwner);
        }

        Result result= new Result(formattedDate, cashRaised, totalShares, shareOwnerList);
        return result;
    }
}
