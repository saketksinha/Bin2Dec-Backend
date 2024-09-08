package com.example.Bin2Dec.Controller;

import com.example.Bin2Dec.dto.BinaryInput;
import com.example.Bin2Dec.dto.DecimalOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ConversionController {

    @PostMapping("/convert")
    public ResponseEntity<?> convertBinaryToDecimal(@RequestBody BinaryInput input) {
        String binary = input.getBinary();

        try {
            // Validate the binary string
            if (!binary.matches("[01]+")) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse() {
                            @Override
                            public HttpStatusCode getStatusCode() {
                                return null;
                            }

                            @Override
                            public ProblemDetail getBody() {
                                return null;
                            }
                        });
            }

            // Convert binary to decimal
            int decimal = Integer.parseInt(binary, 2);

            // Return the result
            return ResponseEntity.ok(new DecimalOutput(decimal));
        } catch (NumberFormatException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse() {
                        @Override
                        public HttpStatusCode getStatusCode() {
                            return null;
                        }

                        @Override
                        public ProblemDetail getBody() {
                            return null;
                        }
                    });
        }
    }
}
