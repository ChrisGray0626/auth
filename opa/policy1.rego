package pers.ruizhi.auth

import rego.v1

# Default accessiable assignments
accessAssignments contains id if {
    assignment := input.assignments[_]
    id := assignment.id
    assignment.order in [1, 2, 3, 4, 5]
}
# Check assignment 6 is accessiable
accessAssignments contains id if {
    assignment := input.assignments[_]
    id := assignment.id
    assignment.order == 6

    every preOrder in [4, 5] {
        preAssignment := input.assignments[_]
        preAssignment.order == preOrder
        preAssignmentId := preAssignment.id
        submission := input.submissions[_]
        submission.assignmentId == preAssignmentId
    }
}
# Check assignment 7 is accessiable
accessAssignments contains id if {
    assignment := input.assignments[_]
    id := assignment.id
    assignment.order == 7

    every preOrder in [4] {
        preAssignment := input.assignments[_]
        preAssignment.order == preOrder
        preAssignmentId := preAssignment.id
        submission := input.submissions[_]
        # Check the submission grade
        submission.grade >= 0.5
    }
}