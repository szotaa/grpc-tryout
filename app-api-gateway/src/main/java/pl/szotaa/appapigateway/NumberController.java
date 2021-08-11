package pl.szotaa.appapigateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szotaa.grpc_tryout.proto.AddNumberRequest;
import pl.szotaa.grpc_tryout.proto.CalculateAvgRequest;
import pl.szotaa.grpc_tryout.proto.InMemoryPersistenceServiceGrpc;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
class NumberController {

    @GrpcClient("InMemoryPersistenceService")
    private InMemoryPersistenceServiceGrpc.InMemoryPersistenceServiceBlockingStub client;

    @PostMapping("/{number}")
    public ResponseEntity<?> addNumber(@PathVariable Integer number) {
        var request = AddNumberRequest.newBuilder().setNumber(number).build();
        var response = client.addNumber(request).getNumberList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/avg")
    public ResponseEntity<?> getAvg() {
        var request = CalculateAvgRequest.newBuilder().build();
        var avg = client.calculateAvg(request).getAverage();
        return ResponseEntity.ok(avg);
    }
}
