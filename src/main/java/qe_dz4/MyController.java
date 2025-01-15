package qe_dz4;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyController {

	private int errorsRate = 5;

	@PostMapping("/datetime")
	public ResponseEntity<TimeResponse> postTime(@RequestBody String body) {
		ObjectMapper objectMapper = new ObjectMapper();
		TimeRequest timeRequest;
		try {
			timeRequest = objectMapper.readValue(body, TimeRequest.class);
		} catch (JsonProcessingException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new TimeResponse("Invalid JSON format: " + e.getMessage(), null));
		}
		delay();
		Random random = new Random();
		if (random.nextInt(100) < errorsRate)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TimeResponse("Special error",null));

		if (isDateTime(timeRequest.getTime())) {
			try {
				TimeResponse timeResponse = new TimeResponse();
				timeResponse.setPostedTime(timeRequest.getTime());
				timeResponse.setMessage("Time posted successfully!");

				return ResponseEntity.ok(timeResponse);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TimeResponse("Error processing request: " + e.getMessage(),null));
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TimeResponse("Invalid time format: " + timeRequest.getTime(),null));
		}
	}

	@PutMapping("/datetime")
	public ResponseEntity<TimeResponse> putTime(@RequestBody String body) {
		ObjectMapper objectMapper = new ObjectMapper();
		TimeRequest timeRequest;
		try {
			timeRequest = objectMapper.readValue(body, TimeRequest.class);
		} catch (JsonProcessingException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new TimeResponse("Invalid JSON format: " + e.getMessage(), null));
		}
		delay();
		Random random = new Random();
		if (random.nextInt(100) < errorsRate)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TimeResponse("Special error",null));

		if (isDateTime(timeRequest.getTime())) {
			try {
				TimeResponse timeResponse = new TimeResponse();
				timeResponse.setPostedTime(timeRequest.getTime());
				timeResponse.setMessage("Time put successfully!");

				return ResponseEntity.ok(timeResponse);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TimeResponse("Error processing request: " + e.getMessage(),null));
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TimeResponse("Invalid time format: " + timeRequest.getTime(),null));
		}
	}

	private boolean isDateTime(String time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		try {
			LocalTime.parse(time, formatter);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	private void delay() {
		Random random = new Random();
		int delaySeconds = random.nextInt(2) + 2;
		try {
			Thread.sleep(delaySeconds * 100);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}


