package greeter

import spock.lang.Specification

class GreetingFormatterSpec extends  Specification{
    def 'Creating greeting'() {
            expect: 'The greeting to be correctly capitalized'
            GreetingFormatter.greeting('gradlephant')== 'Hello ,Gradlephant'
    }
}
