package pers.ruizhi.auth

import rego.v1

# Default accessiable assignments
accessAssignments contains id if {
    assignment := input.assignments[_]
    assignment.order in [1, 4, 5]
    id := assignment.id
}
# Check assignment 2, 3 is accessible
accessAssignments contains id if {
    assignment := input.assignments[_]
    assignment.order in [2, 3]
    id := assignment.id
    assignment.order > 1
    preOrder = assignment.order - 1
    preAssignment := input.assignments[_]
    preOrder == preAssignment.order
    preAssignmentId := preAssignment.id
    submission := input.submissions[_]
    submission.assignmentId == preAssignmentId
}
# Check assignment 6 is accessible
accessAssignments contains id if {
    assignment := input.assignments[_]
    assignment.order == 6
    id := assignment.id

    every preOrder in [4, 5] {
        preAssignment := input.assignments[_]
        preAssignment.order == preOrder
        preAssignmentId := preAssignment.id
        submission := input.submissions[_]
        submission.assignmentId == preAssignmentId
    }
}
# Check assignment 7 is accessible
accessAssignments contains id if {
    assignment := input.assignments[_]
    id := assignment.id
    assignment.order == 7

    every preOrder in [4] {
        preAssignment := input.assignments[_]
        preAssignment.order == preOrder
        preAssignmentId := preAssignment.id
        submission := input.submissions[_]
        submission.assignmentId == preAssignmentId
        # Check the submission grade
        submission.grade >= 0.5
    }
}