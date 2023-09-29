package com.vista.accouting.util.log;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Component
public class JsonDateSerializer extends JsonSerializer<LocalDate>
{
    // ISO 8601
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void serialize(LocalDate date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException
    {
        try {
//            String formattedDate = dateFormat.format(date);
            String formattedDate = date.toString();
            gen.writeString(formattedDate);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
