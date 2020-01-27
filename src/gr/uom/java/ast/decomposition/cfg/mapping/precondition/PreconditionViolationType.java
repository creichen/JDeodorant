package gr.uom.java.ast.decomposition.cfg.mapping.precondition;

public enum PreconditionViolationType {
	// "1" to "8" correspond to the first paper
	// "L1" to "L6" correspond to the lambdas paper, but there is sharing:
	//    8 = L2 (we skip L2)
	//    7 = L3 (we skip L3)
	// Different cases indicated by ".a", ".b" etc.
	// Not mentioned in the papers:
	//    X0: no matched statements
	//    X1: case without switch
	//    X2: super(...) in constructor
	//    X3: super.m(...) in method
	//    X4: this(...) in constructor (`this constructor call')
	//    X5: merging calls f.m(...,x,...) and f.m(...,y,...) where type-of(x) and type-of(y) have no common ancestor
	//    X6: merging variables ty1 x; and ty2 x; where ty1, ty2 have no common ancestor
	//    X7: try { f.m(); } where `try' is merged but `f.m()' is not
	//    X8: 2 or more updates to the same variable found in unmatched statements (may be buggy)
	// L1 L5 L6
	
	EXPRESSION_DIFFERENCE_CANNOT_BE_PARAMETERIZED("1"),
	EXPRESSION_DIFFERENCE_IS_FIELD_UPDATE("3"),
	EXPRESSION_DIFFERENCE_IS_VOID_METHOD_CALL("4"),
	EXPRESSION_DIFFERENCE_IS_METHOD_CALL_THROWING_EXCEPTION_WITHIN_MATCHED_TRY_BLOCK("X7"),
	INFEASIBLE_UNIFICATION_DUE_TO_VARIABLE_TYPE_MISMATCH("X6"),
	INFEASIBLE_UNIFICATION_DUE_TO_MISSING_MEMBERS_IN_THE_COMMON_SUPERCLASS("2.a"),
	INFEASIBLE_UNIFICATION_DUE_TO_PASSED_ARGUMENT_TYPE_MISMATCH("X5"),
	UNMATCHED_STATEMENT_CANNOT_BE_MOVED_BEFORE_OR_AFTER_THE_EXTRACTED_CODE("5.a"),
	UNMATCHED_STATEMENT_CANNOT_BE_MOVED_BEFORE_THE_EXTRACTED_CODE_DUE_TO_CONTROL_DEPENDENCE("5.b"),
	UNMATCHED_BREAK_STATEMENT("5.c"),
	UNMATCHED_CONTINUE_STATEMENT("5.d"),
	UNMATCHED_RETURN_STATEMENT("5.e"),
	UNMATCHED_THROW_STATEMENT("5.f"),
	UNMATCHED_EXCEPTION_THROWING_STATEMENT_NESTED_WITHIN_MATCHED_TRY_BLOCK("5.g"),
	MULTIPLE_RETURNED_VARIABLES("6.a"),
	UNEQUAL_NUMBER_OF_RETURNED_VARIABLES("6.b"),
	SINGLE_RETURNED_VARIABLE_WITH_DIFFERENT_TYPES("6.c"),
	BREAK_STATEMENT_WITHOUT_LOOP("8.a"),
	CONTINUE_STATEMENT_WITHOUT_LOOP("8.b"),
	CONDITIONAL_RETURN_STATEMENT("7"),
	SWITCH_CASE_STATEMENT_WITHOUT_SWITCH("X1"),
	SUPER_CONSTRUCTOR_INVOCATION_STATEMENT("X2"),
	SUPER_METHOD_INVOCATION_STATEMENT("X3"),
	MULTIPLE_UNMATCHED_STATEMENTS_UPDATE_THE_SAME_VARIABLE("X8"),
	INFEASIBLE_REFACTORING_DUE_TO_UNCOMMON_SUPERCLASS("2.b"),
	INFEASIBLE_REFACTORING_DUE_TO_ZERO_MATCHED_STATEMENTS("X0"),
	NOT_ALL_POSSIBLE_EXECUTION_FLOWS_END_IN_RETURN("L4"), // really?
	THIS_CONSTRUCTOR_INVOCATION_STATEMENT("X4");
	
	private String precondition_id;
	private PreconditionViolationType(String precondition_nr) {
		this.precondition_id = precondition_nr;
	}
	
	public String
	getPreconditionId() {
		return this.precondition_id;
	}
	
	public String toString() {
		if(name().equals(EXPRESSION_DIFFERENCE_CANNOT_BE_PARAMETERIZED.name())) {
			return "cannot be parameterized, because it has dependencies to/from statements that will be extracted";
		}
		else if(name().equals(EXPRESSION_DIFFERENCE_IS_FIELD_UPDATE.name())) {
			return "is a field being modified, and thus it cannot be parameterized";
		}
		else if(name().equals(EXPRESSION_DIFFERENCE_IS_VOID_METHOD_CALL.name())) {
			return "is a void method call, and thus it cannot be parameterized";
		}
		else if(name().equals(EXPRESSION_DIFFERENCE_IS_METHOD_CALL_THROWING_EXCEPTION_WITHIN_MATCHED_TRY_BLOCK.name())) {
			return "is a method call throwing exception(s) that should be caught by a try block that will be extracted";
		}
		else if(name().equals(UNMATCHED_STATEMENT_CANNOT_BE_MOVED_BEFORE_OR_AFTER_THE_EXTRACTED_CODE.name())) {
			return "cannot be moved before or after the extracted code, because it has dependencies to/from statements that will be extracted";
		}
		else if(name().equals(UNMATCHED_STATEMENT_CANNOT_BE_MOVED_BEFORE_THE_EXTRACTED_CODE_DUE_TO_CONTROL_DEPENDENCE.name())) {
			return "cannot be moved before the extracted code, because it has control dependencies from statements that will be extracted";
		}
		else if(name().equals(MULTIPLE_UNMATCHED_STATEMENTS_UPDATE_THE_SAME_VARIABLE.name())) {
			return "cannot be moved, because it updates a variable modified in other unmapped statements";
		}
		else if(name().equals(UNMATCHED_EXCEPTION_THROWING_STATEMENT_NESTED_WITHIN_MATCHED_TRY_BLOCK.name())) {
			return "cannot be moved before or after the extracted code, because it throws exception(s) that should be caught by a try block that will be extracted";
		}
		else if(name().equals(SUPER_CONSTRUCTOR_INVOCATION_STATEMENT.name())) {
			return "cannot be extracted from constructor";
		}
		else if(name().equals(THIS_CONSTRUCTOR_INVOCATION_STATEMENT.name())) {
			return "cannot be extracted from constructor";
		}
		else if(name().equals(SUPER_METHOD_INVOCATION_STATEMENT.name())) {
			return "cannot be extracted from method";
		}
		return "";
	}
}
