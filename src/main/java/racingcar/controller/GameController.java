package racingcar.controller;

import java.util.List;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.model.RandomNumberGenerator;
import racingcar.model.Round;
import racingcar.util.ExceptionRoofer;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Cars cars = getCars();
        final Round round = getRound();

        play(round, cars);

        outputView.printCarsPosition(cars.getCars());

        final List<String> winnersName = findWinnersName(cars);
        outputView.printWinners(winnersName);
    }

    private Cars getCars() {
        return ExceptionRoofer.generate(() -> {
            final String names = inputView.readCarNames();
            return Cars.from(names);
        }, outputView::printError);
    }

    private Round getRound() {
        return ExceptionRoofer.generate(() -> {
            final String tryRound = inputView.readTryRound();
            return Round.from(tryRound);
        }, outputView::printError);
    }

    private void play(final Round round, final Cars cars) {
        final RandomNumberGenerator generator = new RandomNumberGenerator();

        while (round.isContinue()) {
            cars.go(generator);
            round.progress();

            outputView.printCarsPosition(cars.getCars());
        }
    }

    private List<String> findWinnersName(final Cars cars) {
        return cars.findWinner()
                .stream()
                .map(Car::getName)
                .toList();
    }
}
