# cf-java [![Build Status](https://travis-ci.org/joergen7/cf-java.svg?branch=master)](https://travis-ci.org/joergen7/cf-java)

###### Java bindings for Cuneiform

This Java library is used to implement Java-based execution environments for the Cuneiform workflow language.

cf-java encapsulates all communication with a Cuneiform interpreter service as implemented by [cf_lang](https://github.com/joergen7/cf_lang). cf-java is used to send a workflow to the interpreter, receive workflow task invocations which can be executed using [effi](https://github.com/joergen7/effi), send back the invocation result to the interpreter service, and receive the workflow result once all task invocations have been processed.

The communication protocols must be expected to change over time. Thus, [cf-java 0.0.3-RELEASE](https://github.com/joergen7/cf-java/releases/tag/0.0.3-RELEASE) is compatible with [cf_lang 0.1.0](https://github.com/joergen7/cf_lang/releases/tag/0.1.0) and [effi 0.1.3](https://github.com/joergen7/effi/releases/tag/0.1.3). No other version combination of these tools can be expected to work.

## Usage

### Adding cf-java to a Maven project

The following dependencies and repositories need to be added to your `pom.xml` to add the cf-java library to the dependencies of a given Maven project:

    <dependencies>
        <dependency>
            <groupId>de.hu-berlin.wbi</groupId>
            <artifactId>cf-java</artifactId>
            <version>0.0.3-RELEASE</version>
        </dependency>
    </dependencies>

    <repositories>
        <repository>
            <id>ossrh</id>
            <url>https://oss.sonatype.org/content/groups/public</url>
        </repository>
    </repositories>

### Workflow Life Cycle

This library controls the complete life cycle of a workflow, i.e., it is used to drive a workflow from the user perspective:

- committing a workflow source
- receiving a workflow result

as well as from the perspective of the (possibly distributed) execution environment:

- receiving a task invocation from the interpreter
- committing a task result after executing it.

To the outside, the cf-java library connects to an interpreter instance via a TCP connection which is held open for the duration of the workflow run. To the inside, it appears as a passive Java object which can be queried. Herein, none of the methods are actually blocking, enabling a workflow driver (user interface plus execution environment) to interact asynchronously with the interpreter service, which lives in its own domain.

This is achieved by interacting with an instance of the `RemoteWorkflow` class which is described below.

#### Creating a Remote Workflow Object

Say we are about to run a workflow that returns the string `"Hello world"`. The corresponding Cuneiform program would be:

    "Hello world";

To establish a connection to an instance of a Cuneiform interpreter service running at `localhost`, a `RemoteWorkflow` instance is generated as follows:

    import de.huberlin.wbi.cfjava.cuneiform.RemoteWorkflow;

    ...

    RemoteWorkflow wf = new RemoteWorkflow( "\"Hello world.\";\n\n" );

This command also sends the workflow source in the form of a string to the workflow interpreter, which starts to evaluate the workflow independently upon reception. We can also pass the constructor a second string identifying the host where the interpreter service is running. If the interpreter runs, e.g., at the IP address `192.127.12.345` we can create a connection by entering

    RemoteWorkflow wf = new RemoteWorkflow( "\"Hello world.\";\n\n", "192.127.12.345" );

## Resources

- [cf_lang](https://github.com/joergen7/cf_lang). An Erlang-based web service implementing a Cuneiform interpreter.
- [effi](https://github.com/joergen7/effi). A command-line tool for executing single Cuneiform invocations.

## Authors

- Jörgen Brandt (joergen7) [joergen.brandt@onlinehome.de](mailto:joergen.brandt@onlinehome.de)

## License

[Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0.html)
