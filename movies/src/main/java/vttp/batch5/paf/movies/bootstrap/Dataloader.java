package vttp.batch5.paf.movies.bootstrap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.stereotype.Component;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@Component
public class Dataloader {

  // TODO: Task 2
  public List<JsonObject> load(String dataPath) {

    List<JsonObject> filteredJsonObjList = new LinkedList<>();

    try (ZipFile zipFile = new ZipFile("../" + dataPath)) {
      Enumeration<? extends ZipEntry> entries = zipFile.entries();

      while (entries.hasMoreElements()) {
        ZipEntry entry = entries.nextElement();
        InputStream is = zipFile.getInputStream(entry);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date filterDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-12-31");

        while ((line = br.readLine()) != null) {
          JsonObject jsonObject = Json.createReader(new StringReader(line)).readObject();

          Date releaseDate = sdf.parse(jsonObject.getString("release_date"));             

          if (releaseDate.after(filterDate)) {
            filteredJsonObjList.add(jsonObject);
          }
        }

        br.close();
      }

    } catch (IOException e) {
      System.err.println("Error reading movies.zip");
      e.printStackTrace();
    } catch (ParseException e) {
      System.err.println("Date parsing error");
      e.printStackTrace();
    }

    return filteredJsonObjList;

  }

}
