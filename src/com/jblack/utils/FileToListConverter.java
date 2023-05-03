package com.jblack.utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FileToListConverter {
    public static List<Map<String, String>> fileToList(File file) throws IOException {
        // https://stackoverflow.com/questions/20068383/convert-csv-values-to-a-hashmap-key-value-pairs-in-java
        List<Map<String, String>> dataList = new LinkedList<>();
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        MappingIterator<Map<String, String>> iterator = mapper.readerFor(Map.class)
                .with(schema)
                .readValues(file);
        while (iterator.hasNext()) {
            dataList.add(iterator.next());
        }
        return dataList;
    }
}
