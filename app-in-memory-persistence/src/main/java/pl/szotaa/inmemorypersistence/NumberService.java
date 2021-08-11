package pl.szotaa.inmemorypersistence;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import pl.szotaa.grpc_tryout.proto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@GrpcService
class NumberService extends InMemoryPersistenceServiceGrpc.InMemoryPersistenceServiceImplBase {

    private final List<Integer> numbers = new CopyOnWriteArrayList<>();

    @Override
    public void addNumber(AddNumberRequest request, StreamObserver<AddNumberResponse> responseObserver) {
        var number = request.getNumber();
        numbers.add(number);
        var response = AddNumberResponse.newBuilder()
                .addAllNumber(numbers)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void calculateAvg(CalculateAvgRequest request, StreamObserver<CalculateAvgResponse> responseObserver) {
        var tempList = new ArrayList<>(numbers);
        var avg = tempList.stream().mapToInt(Integer::intValue).average().orElse(0);
        var response = CalculateAvgResponse.newBuilder()
                .setAverage(avg)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
