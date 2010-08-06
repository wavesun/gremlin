package com.tinkerpop.gremlin.compiler.functions.g.graph;

import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.gremlin.compiler.types.Atom;
import com.tinkerpop.gremlin.compiler.context.GremlinScriptContext;
import com.tinkerpop.gremlin.compiler.functions.AbstractFunction;
import com.tinkerpop.gremlin.compiler.functions.FunctionHelper;
import com.tinkerpop.gremlin.compiler.operations.Operation;

import java.util.List;

/**
 * @author Pavel A. Yaskevich
 */
public class IdVertexFunction extends AbstractFunction<Vertex> {

    private final static String FUNCTION_NAME = "id-v";

    public Atom<Vertex> compute(final List<Operation> parameters, final GremlinScriptContext context) throws RuntimeException {
        final int size = parameters.size();
        if (size == 0 || size > 2)
            throw new RuntimeException(this.createUnsupportedArgumentMessage());

        final Graph graph = FunctionHelper.getGraph(parameters, 0, context);
        final Object identifer;

        if (size == 2) {
            identifer = parameters.get(1).compute().getValue();
        } else {
            identifer = parameters.get(0).compute().getValue();
        }

        return new Atom<Vertex>(graph.getVertex(identifer));
    }

    public String getFunctionName() {
        return FUNCTION_NAME;
    }
}