package pers.ruizhi.auth.policy1

import rego.v1

accessAssignments contains id if {
    assignment := input.assignments[_]
    id := assignment.id
    assignment.order == 1
}

accessAssignments contains id if {
    assignment := input.assignments[_]
    id := assignment.id
    assignment.order > 1
    preOrder = assignment.order - 1
    preAssignment := input.assignments[_]
    preOrder == preAssignment.order
    preAssignmentId := preAssignment.id
    submission := input.submissions[_]
    submission.assignmentId == preAssignmentId
}