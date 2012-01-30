package com.dsindigo.boletas

class StudentsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [studentsInstanceList: Students.list(params), studentsInstanceTotal: Students.count()]
    }

    def create = {
        def studentsInstance = new Students()
        studentsInstance.properties = params
        return [studentsInstance: studentsInstance]
    }

    def save = {
        def studentsInstance = new Students(params)
        if (studentsInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'students.label', default: 'Students'), studentsInstance.id])}"
            redirect(action: "show", id: studentsInstance.id)
        }
        else {
            render(view: "create", model: [studentsInstance: studentsInstance])
        }
    }

    def show = {
        def studentsInstance = Students.get(params.id)
        if (!studentsInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'students.label', default: 'Students'), params.id])}"
            redirect(action: "list")
        }
        else {
            [studentsInstance: studentsInstance]
        }
    }

    def edit = {
        def studentsInstance = Students.get(params.id)
        if (!studentsInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'students.label', default: 'Students'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [studentsInstance: studentsInstance]
        }
    }

    def update = {
        def studentsInstance = Students.get(params.id)
        if (studentsInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (studentsInstance.version > version) {
                    
                    studentsInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'students.label', default: 'Students')] as Object[], "Another user has updated this Students while you were editing")
                    render(view: "edit", model: [studentsInstance: studentsInstance])
                    return
                }
            }
            studentsInstance.properties = params
            if (!studentsInstance.hasErrors() && studentsInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'students.label', default: 'Students'), studentsInstance.id])}"
                redirect(action: "show", id: studentsInstance.id)
            }
            else {
                render(view: "edit", model: [studentsInstance: studentsInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'students.label', default: 'Students'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def studentsInstance = Students.get(params.id)
        if (studentsInstance) {
            try {
                studentsInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'students.label', default: 'Students'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'students.label', default: 'Students'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'students.label', default: 'Students'), params.id])}"
            redirect(action: "list")
        }
    }
}
